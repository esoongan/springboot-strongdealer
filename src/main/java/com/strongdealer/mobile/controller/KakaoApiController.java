package com.strongdealer.mobile.controller;

import com.strongdealer.mobile.service.KakaoApiService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
public class KakaoApiController {

    private final KakaoApiService kakaoApiService;

    @GetMapping("/api/user/kakao/oauth")
    public String kakaoLogin(@RequestParam String code) {

        log.info("인가코드" + code);


        String accessToken = kakaoApiService.requestAccessToken(code);


        return "";

    }

}
