package com.strongdealer.mobile.service;

import com.strongdealer.mobile.domain.Car.CarInfo.Car;
import com.strongdealer.mobile.domain.Car.CarInfo.CarRepository;
import com.strongdealer.mobile.dto.Car.CarInstanceResponseDto;
import com.strongdealer.mobile.dto.Car.CarResponseDto;
import com.strongdealer.mobile.dto.Car.CarResponseDtoFromNation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class CarService {

    private final CarRepository carRepository;



    // 국가api호출
    @Transactional
    public CarResponseDto getCarInfobyCarNo(String carNo) {
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
}
