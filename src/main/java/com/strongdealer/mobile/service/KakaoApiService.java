package com.strongdealer.mobile.service;

import com.strongdealer.mobile.key.RestApiKey;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.json.JsonParser;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.HashMap;
import java.util.Map;


@Slf4j
@Service
public class KakaoApiService {

    @Transactional
    public String requestAccessToken(String authorizeCode){
        // 앱 생성시 발급받은 REST API ( 노출을 막기위해 외부 class(git ignore에 추가한 클래스)에 private필드로 선언함)
        final String REST_API_KEY = RestApiKey.getRestApiKey();
        // 요청보낼 카카오 서버 url
        final String requestURL = "https://kauth.kakao.com/oauth/token";
        // 인가코드가 리다이렉트된 URI
        final String redirectURI = "http://localhost:8080/api/user/kakao/oauth";
        // 인가코드 받기 요청으로 얻은 인가 코드 - 파라미터로 넘어온 authorizeCdoe

        // 응답으로 받을 사용자토큰
        String accessToken = "";
        String refreshToken = "";

        // 요청에 포함시킬 Parameter
        HashMap<String, String> params = new HashMap<>();
        HashMap<String, Object> result = new HashMap<>();

        try {
            // 헤더정의, url정의, restTemplate.exchange()메서드로 api호출

            HttpHeaders headers = new HttpHeaders();
            HttpEntity<?> entity = new HttpEntity<>(headers);

            // URL에 쿼리파라미터추가
            UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(requestURL)
                    .queryParam("grant_type", "authorization_code")
                    .queryParam("client_id", REST_API_KEY)
                    .queryParam("redirect_uri", redirectURI)
                    .queryParam("code", authorizeCode);


            // TODO: autowired로 수정하기
            RestTemplate restTemplate = new RestTemplate();
            // 순서대로 url, method, requestEntity, reponseType(클래스), uriVariables
            ResponseEntity<Map> resultMap = restTemplate.exchange(uriBuilder.toUriString(), HttpMethod.POST, entity, Map.class, params);

            log.info("사용자토큰요청응답" + resultMap.toString());

            HttpStatus status = resultMap.getStatusCode();


            String access_token = (String) resultMap.getBody().get("access_token");
            Integer expires_in = (Integer) resultMap.getBody().get("expires_in");
            String refresh_token = (String) resultMap.getBody().get("refresh_token");
            Integer refresh_token_expires_in = (Integer) resultMap.getBody().get("refresh_token_expires_in");
            String scope = (String) resultMap.getBody().get("scope");

            return access_token;


        } catch (HttpClientErrorException | HttpServerErrorException e) {
            log.info(String.valueOf(e.getRawStatusCode()));
            log.info(e.getStatusText());
        }

        return null;

    }

}
