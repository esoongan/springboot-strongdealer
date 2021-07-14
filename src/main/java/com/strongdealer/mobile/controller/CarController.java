package com.strongdealer.mobile.controller;

import com.strongdealer.mobile.dto.Car.CarOption.CarOptionRequestDto;
import com.strongdealer.mobile.dto.Car.CarRequestDto;
import com.strongdealer.mobile.dto.Car.CarResponseDto;
import com.strongdealer.mobile.model.ApiResponse;
import com.strongdealer.mobile.model.HttpResponseMessage;
import com.strongdealer.mobile.model.HttpStatusCode;
import com.strongdealer.mobile.service.CarService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class CarController {

    private final CarService carService;

    // 차 번호로 차정보 조회 - 외부api와 연동해야함
    @GetMapping("/api/car")
    public ResponseEntity<ApiResponse<CarResponseDto>> getCarInfo(@RequestParam String carNo, @RequestHeader String accessToken) {
        CarResponseDto responseDto = carService.getCarInfobyCarNo(carNo, accessToken);

        return new ResponseEntity<>(
                ApiResponse.response(
                        HttpStatusCode.OK,
                        HttpResponseMessage.GET_SUCCESS,
                        responseDto), HttpStatus.OK
        );

    }

    // 사용자로부터 확인되고 추가된 정보로 DB업데이트 + 차량 등록 (현재시간도 저장)
    @PostMapping("/api/car")
    public ResponseEntity<ApiResponse<CarResponseDto>> registerCar4Sale(@RequestBody CarRequestDto requestDto, Authentication authentication) {
        CarResponseDto responseDto = carService.registerCar4Sale(requestDto, authentication);

        return new ResponseEntity<>(
                ApiResponse.response(
                        HttpStatusCode.OK,
                        HttpResponseMessage.POST_SUCCESS,
                        responseDto), HttpStatus.OK
        );

    }

    // 옵션저장 ( 응답 수정해야함 )
    @PostMapping("/api/car/option")
    public ResponseEntity<Long> saveCarOption(@RequestBody CarOptionRequestDto requestDto) {
        Long id = carService.saveCarOption(requestDto);
        return new ResponseEntity<Long>(HttpStatus.CREATED);
    }
}
