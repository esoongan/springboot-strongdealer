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
import com.strongdealer.mobile.dto.Car.CarRequestDto;
import com.strongdealer.mobile.dto.Car.CarResponseDto;
import com.strongdealer.mobile.dto.Car.CarResponseDtoFromNation;
import com.strongdealer.mobile.exception.CarNotFoundException;
import com.strongdealer.mobile.exception.UserNotFoundException;
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

    // 국가api호출
    @Transactional
    public CarResponseDto getCarInfobyCarNo(String carNo, String accessToken) {
        // 1. 우리 DB에 해당 차번호가 저장되어있는지 찾고 없으면 국가 API호출




        // api호출 -> 응답 받아온다.
        // 받아온 응답 파싱해서 DB에 선저장 -> responseDto로 리턴

        // 국가로부터 받아왔다 치고
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

        // 응답받은 차 정보 DB에 저장 -> 한번 조회한 차에 대해서는 더이상 조회하지 않아도 됨
        Car car = carRepository.save(responseDto.toEntity());





        return new CarResponseDto(car);

    }

    // 사용자로부터 확인후, 변할건 변하고 다시 요청받아서 디비에 업뎃
    @Transactional
    public Car updateCarInfo(CarRequestDto requestDto) {

        Car car = carRepository.findById(requestDto.getId())
                .orElseThrow(() -> new CarNotFoundException(requestDto.getId()));

        return car;

    }

    // 차량 견적신청 등록
    @Transactional
    public CarResponseDto registerCar4Sale(CarRequestDto requestDto, Authentication authentication) {
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
