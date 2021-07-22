package com.strongdealer.mobile.service;

import com.strongdealer.mobile.domain.Car.Car4Sale.Car4Sale;
import com.strongdealer.mobile.domain.Car.Car4Sale.Car4SaleRepository;
import com.strongdealer.mobile.domain.Car.CarInfo.Car;
import com.strongdealer.mobile.domain.User.User;
import com.strongdealer.mobile.dto.Car.Car4Sale.Car4SaleResponseDto;
import com.strongdealer.mobile.dto.Car.CarResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

@RequiredArgsConstructor
@Service
public class Car4SaleService {

    private final Car4SaleRepository car4SaleRepository;
    private final UserService userService;



    @Transactional
    public Car4SaleResponseDto getMyAuction(Authentication authentication) {
        User user = userService.getUserByToken(authentication.getPrincipal());
        // 거래를 찾기
        Car4Sale car4Sale = car4SaleRepository.findByUserId(user.getId())
                .orElseThrow(() -> new NoSuchElementException("등록된 차량이 없습니다."));

        return new Car4SaleResponseDto(car4Sale);






    }
}
