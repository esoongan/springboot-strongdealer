package com.strongdealer.mobile.service;

import com.strongdealer.mobile.dto.Car.CarInstanceResponseDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CarService {

    @Transactional
    public CarInstanceResponseDto getCarInfobyCarNo(String carNo) {
        // api호출 -> 응답 받아온다.
        // 받아온 응답 파싱해서 responseDto로 리턴

        CarInstanceResponseDto carInstanceResponseDto =
                CarInstanceResponseDto.builder()
                        .modelName("쌍용 티볼리")
                        .rating("1.5가솔린 터보")
                        .fuel("가솔린")
                        .color("흰색")
                        .gearbox("오토")
                        .modelYear(2022)
                        .initialRegistration(2020)
                        .price(2000)
                        .build();

        return carInstanceResponseDto;

    }
}
