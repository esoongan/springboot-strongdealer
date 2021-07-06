package com.strongdealer.mobile.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;
import com.strongdealer.mobile.domain.User.UserRepository;
import com.strongdealer.mobile.dto.Kakao.KakaoUserResponseDto;
import com.strongdealer.mobile.key.RestApiKey;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.json.JsonParseException;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.StringReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Slf4j
@RequiredArgsConstructor
@Service
public class KakaoApiService {

    // 앱 생성시 발급받은 REST API ( 노출을 막기위해 외부 class(git ignore에 추가한 클래스)에 private필드로 선언함)
    private static final String REST_API_KEY = RestApiKey.getRestApiKey();
    private final UserRepository userRepository;


    @Transactional
    public String requestAccessToken(String authorizeCode){

        // 요청보낼 카카오 서버 url
        final String requestURL = "https://kauth.kakao.com/oauth/token";
        // 인가코드가 리다이렉트된 URI
        final String redirectURI = "http://localhost:8080/api/user/kakao/oauth";
        // 인가코드 받기 요청으로 얻은 인가 코드 - 파라미터로 넘어온 authorizeCdoe

        // 응답으로 받을 사용자토큰
        String access_token;
        String refresh_token;

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


            access_token = (String) resultMap.getBody().get("access_token");
            Integer expires_in = (Integer) resultMap.getBody().get("expires_in");
            refresh_token = (String) resultMap.getBody().get("refresh_token");
            Integer refresh_token_expires_in = (Integer) resultMap.getBody().get("refresh_token_expires_in");
            String scope = (String) resultMap.getBody().get("scope");



            return access_token;


        } catch (HttpClientErrorException | HttpServerErrorException e) {
            log.info(String.valueOf(e.getRawStatusCode()));
            log.info(e.getStatusText());
        }

        return null;

    }

    // 카카오서버로 사용자정보 요청
    @Transactional
    public KakaoUserResponseDto getUserInfo(String access_token) {
        // 요청보낼 카카오 서버 url
        final String requestURL = "https://kapi.kakao.com/v2/user/me";
        final String authorization_header_value = "Bearer " + access_token;

        try {
            // 헤더생성 및 값추가
            HttpHeaders headers = new HttpHeaders();
            headers.add("Authorization", authorization_header_value);

            // 요청 엔티티 객체 생성
            HttpEntity<?> entity = new HttpEntity<>(headers);

            // TODO: autowired로 수정하기
            RestTemplate restTemplate = new RestTemplate();
            // 순서대로 url, method, requestEntity, reponseType(클래스), uriVariables
            ResponseEntity<String> resultMap = restTemplate.exchange(requestURL, HttpMethod.POST, entity, String.class);

            log.info("사용자 정보 추출 성공");

            String jsonString = resultMap.getBody();

//            JsonReader reader = new JsonReader(new StringReader(jsonString));
//            reader.setLenient(true);

            JsonElement element = JsonParser.parseString(jsonString);
            JsonObject object = element.getAsJsonObject();

            Long id = object.get("id").getAsLong();
            JsonObject properties = object.get("properties").getAsJsonObject();
            String profile_image = properties.get("profile_image").getAsString();
            String thumbnail_image = properties.get("thumbnail_image").getAsString();
            JsonObject kakao_account = object.get("kakao_account").getAsJsonObject();
            String email = kakao_account.get("email").getAsString();
            String gender = kakao_account.get("gender").getAsString();

            KakaoUserResponseDto responseDto = KakaoUserResponseDto.builder()
                    .id(id)
                    .profile_image(profile_image)
                    .thumbnail_image(thumbnail_image)
                    .email(email)
                    .gender(gender)
                    .build();

            return responseDto;

            //응답데이터중 아이디만 먼저 파싱해서 DB에서 조회

            // 0. 우선 해당 아이디와 권한을 담은 JWT토큰 발행

            //1. DB에 회원정보가 없으면 userRequestDto에 저장해서 DB에 1차 저장하고 클라이언트 응답으로 토큰과 1차정보 주면서 헤더에 회원가입 필요라고 추가시킴

            // 1.1 추가정보 받아 회원가입 진행 ( 토큰으로부터 회원정보 찾곡 나머지 것들 업데이트)


            // 2.DB에 회원정보가 있으면 로그아웃하고 새로 로그인한것이므로 발급한 JWT토큰 발행

        } catch (HttpClientErrorException | HttpServerErrorException e) {
            log.info(String.valueOf(e.getRawStatusCode()));
            log.info(e.getStatusText());
        }
        return null;
    }


}
