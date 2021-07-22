package com.strongdealer.mobile.controller;

import com.strongdealer.mobile.service.Car4SaleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RequiredArgsConstructor
@Slf4j
@RestController
public class Car4SaleController {

    private final Car4SaleService car4SaleService;

    // 내 차 경매 조회
    @GetMapping
    public void getMyAuction(Authentication authentication) {
        car4SaleService.getMyAuction(authentication);

    }
}
