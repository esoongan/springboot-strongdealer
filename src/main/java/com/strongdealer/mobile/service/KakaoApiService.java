package com.strongdealer.mobile.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class KakaoApiService {

    @Transactional
    public void getAccessToken(String authorizeCode) {
        String requestURL = "https://kauth.kakao.com/oauth/token";
        String accessToken = "";
        String refreshToken = "";

        // 1. requestURL로 POST요청

        // 2. POST요청에 필요로 요구하는 파라미터 전송

        // 3. 요청을 통해 얻은 JSON타입의 Response메세지 읽기

        // 4. JSON파싱해서 객체생성
    }
}
