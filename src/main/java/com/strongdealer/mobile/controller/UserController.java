package com.strongdealer.mobile.controller;

import com.strongdealer.mobile.domain.User.User;
import com.strongdealer.mobile.dto.User.UserRequestDto;
import com.strongdealer.mobile.dto.User.UserResponseDto;
import com.strongdealer.mobile.model.ApiResponse;
import com.strongdealer.mobile.model.HttpResponseMessage;
import com.strongdealer.mobile.model.HttpStatusCode;
import com.strongdealer.mobile.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class UserController {

    private final UserService userService;

    // 카카오 인증후에 추가정보 받아서 회원 DB에 저장, 토큰응답
    @PostMapping("/api/auth/user/join")
    public ResponseEntity<ApiResponse<?>> join(@RequestBody UserRequestDto requestDto){

        userService.registerDone(requestDto);

        String token = "123";

        return new ResponseEntity<>(ApiResponse.response(
                HttpStatusCode.OK,
                HttpResponseMessage.CREATED_USER,
                token), HttpStatus.OK

        );

    }


    // 사용자 정보조회
    @GetMapping
    public ResponseEntity<ApiResponse<UserResponseDto>> getUserInfo(Authentication authentication) {
        User user = userService.getUserByToken(authentication.getPrincipal());

        return new ResponseEntity<>(ApiResponse.response(
                HttpStatusCode.OK,
                HttpResponseMessage.GET_SUCCESS,
                new UserResponseDto(user)), HttpStatus.OK
        );

    }


}
