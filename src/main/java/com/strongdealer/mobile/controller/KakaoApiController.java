package com.strongdealer.mobile.controller;

import com.strongdealer.mobile.dto.Kakao.KakaoUserResponseDto;
import com.strongdealer.mobile.dto.User.UserResponseTempDto;
import com.strongdealer.mobile.model.ApiResponse;
import com.strongdealer.mobile.model.HttpResponseMessage;
import com.strongdealer.mobile.model.HttpStatusCode;
import com.strongdealer.mobile.service.KakaoApiService;
import com.strongdealer.mobile.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RestController
public class KakaoApiController {

    private final KakaoApiService kakaoApiService;
    private final UserService userService;

    // 클라단에서 받은 액세스토큰을 여기로 주면 받아서 새로운 JWT발급후 응답.
    @PostMapping("/api/autj/user/login")
    public ResponseEntity<ApiResponse<?>> generateJWT(@RequestHeader String accessToken) {
        log.info("액세스토큰" + accessToken);

        // 0. 카카오 서버로부터 사용자정보 조회
        KakaoUserResponseDto userResponseDto = kakaoApiService.getUserInfo(accessToken);

        // 1. 기존 회원인지 확인후
        if(userService.is_need_register(userResponseDto.getId())){
            // 기존이면 우리서버의 토큰발급
            String token = "123123";
            return new ResponseEntity<>(ApiResponse.response(
                    HttpStatusCode.OK,
                    HttpResponseMessage.LOGIN_SUCCESS,
                    token), HttpStatus.OK);
        }
        // 기존회원이 아니면 일부사용자정보 응답+ 회원가입 필요를 알림
        else {
            UserResponseTempDto tempDto = userService.register(userResponseDto);

            return new ResponseEntity<>(ApiResponse.response(
                    HttpStatusCode.NO_CONTENT,
                    HttpResponseMessage.NEED_JOIN,
                    tempDto), HttpStatus.OK
            );
        }
    }

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
    }



}
