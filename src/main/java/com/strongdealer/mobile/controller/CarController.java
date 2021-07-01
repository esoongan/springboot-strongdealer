package com.strongdealer.mobile.controller;

import com.strongdealer.mobile.dto.Car.CarInstanceResponseDto;
import com.strongdealer.mobile.model.ApiResponse;
import com.strongdealer.mobile.model.HttpResponseMessage;
import com.strongdealer.mobile.model.HttpStatusCode;
import com.strongdealer.mobile.service.CarService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class CarController {

    private final CarService carService;

    // 차 번호로 차정보 조회 - 외부api와 연동해야함
    @GetMapping("/api/car")
    public ResponseEntity<ApiResponse> getCarInfo(@RequestParam String carNo) {
        CarInstanceResponseDto responseDto = carService.getCarInfobyCarNo(carNo);


        return new ResponseEntity<>(
                ApiResponse.response(
                        HttpStatusCode.OK,
                        HttpResponseMessage.GET_SUCCESS,
                        responseDto), HttpStatus.OK
        );

    }
}
