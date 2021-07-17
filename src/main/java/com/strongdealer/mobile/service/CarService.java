package com.strongdealer.mobile.service;

import com.strongdealer.mobile.domain.Car.Car4Sale.Car4Sale;
import com.strongdealer.mobile.domain.Car.Car4Sale.Car4SaleRepository;
import com.strongdealer.mobile.domain.Car.CarInfo.Car;
import com.strongdealer.mobile.domain.Car.CarInfo.CarRepository;
import com.strongdealer.mobile.domain.Car.CarOption.CarOption;
import com.strongdealer.mobile.domain.Car.CarOption.CarOptionRepository;
import com.strongdealer.mobile.domain.User.User;
import com.strongdealer.mobile.domain.User.UserRepository;
import com.strongdealer.mobile.dto.Car.CarOption.CarOptionRequestDto;
import com.strongdealer.mobile.dto.Car.CarUpdateRequestDto;
import com.strongdealer.mobile.dto.Car.CarResponseDto;
import com.strongdealer.mobile.dto.Car.CarResponseDtoFromNation;
import com.strongdealer.mobile.exception.CarNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class CarService {

    private final UserRepository userRepository;
    private final CarRepository carRepository;
    private final Car4SaleRepository car4SaleRepository;
    private final CarOptionRepository optionRepository;

    private final UserService userService;

    // 차번호로 정보조회
    @Transactional
    public CarResponseDto getCarInfobyCarNo(String carNo) {

        // 1. 우리 DB에 해당 차번호가 저장되어있는지 찾고 없으면 국가 API호출하여 Car엔티티 반환
        Car car = carRepository.findByCarNo(carNo)
                .orElseGet(() -> getCarInfoFromNationApi(carNo));

        return new CarResponseDto(car);
    }

    // DB에 존재하지 않는 차 번호에 대해 국가api 조회하고 DB에 저장한후 저장한 Car엔티티를 리턴
    @Transactional
    public Car getCarInfoFromNationApi(String carNo) {
        // 국가로부터 받아왔다 치고
        // responseDto에 받아온 값 셋팅하고
        CarResponseDtoFromNation responseDto =
                CarResponseDtoFromNation.builder()
                        .carNo(carNo)
                        .manufacturer("기아")
                        .type("SUV")
                        .model("쌍용 티볼리")
                        .detailModel("쌍용 티볼리")
                        .rating("1.5가솔린 터보")
                        .detailRating("터보")
                        .gearbox("오토")
                        .fuel("가솔린")
                        .color("white")
                        .modelYear(2022)
                        .mileage(300)
                        .price(2000)
                        .initialRegistration(2020)
                        .build();
        return carRepository.save(responseDto.toEntity());
    }

    // 사용자로부터 확인후, 변할건 변하고 다시 요청받아서 디비에 업뎃
    @Transactional
    public Car updateCarInfo(CarUpdateRequestDto requestDto) {

        Car car = carRepository.findById(requestDto.getId())
                .orElseThrow(() -> new CarNotFoundException(requestDto.getId()));

        return car.update(requestDto);
    }

    // 차량 견적신청 등록
    @Transactional
    public CarResponseDto registerCar4Sale(CarUpdateRequestDto requestDto, Authentication authentication) {
        // 정보 업데이트된 차 엔티티
        Car car = this.updateCarInfo(requestDto);
        // 토큰으로 사용자 추출
        User user = userService.getUserByToken(authentication.getPrincipal());

        // 관계테이블 설정 -> 등 록
        Car4Sale car4Sale = car4SaleRepository.save(Car4Sale.builder().car(car).user(user).build());

        return new CarResponseDto(car4Sale.getCar());

    }


    // 옵션저장
    @Transactional
    public Long saveCarOption(CarOptionRequestDto requestDto) {
        CarOption carOption = optionRepository.save(requestDto.toEntity());

        Car car = carRepository.findById(requestDto.getId())
                .orElseThrow(() -> new CarNotFoundException(requestDto.getId()));

        // 옵션과 차 관계 셋팅
        car.updateOption(carOption);

        return carOption.getId();

    }
}
