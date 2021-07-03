package com.strongdealer.mobile.controller;

import com.strongdealer.mobile.dto.Kakao.KakaoUserResponseDto;
import com.strongdealer.mobile.model.ApiResponse;
import com.strongdealer.mobile.model.HttpResponseMessage;
import com.strongdealer.mobile.model.HttpStatusCode;
import com.strongdealer.mobile.service.KakaoApiService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
public class KakaoApiController {

    private final KakaoApiService kakaoApiService;

    @GetMapping("/api/user/kakao/oauth")
    public ResponseEntity<ApiResponse<KakaoUserResponseDto>> kakaoLogin(@RequestParam String code) {

        log.info("인가코드" + code);

        String accessToken = kakaoApiService.requestAccessToken(code);

        KakaoUserResponseDto userResponseDto = kakaoApiService.getUserInfo(accessToken);

        return new ResponseEntity<>(
                ApiResponse.response(
                        HttpStatusCode.OK,
                        HttpResponseMessage.GET_SUCCESS,
                        userResponseDto), HttpStatus.OK
        );


//        return "하이루?";

    }



}
