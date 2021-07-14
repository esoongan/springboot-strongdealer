package com.strongdealer.mobile.jwt;

import io.jsonwebtoken.Claims;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
class JwtTokenGeneratorTest {

    @Test
    void 토큰_정상_발급() {

        //given
        JwtTokenGenerator tokenGenerator = new JwtTokenGenerator();

        //when
        String username = "testuser-1";
        String accessToken = tokenGenerator.createToken(username);


        //then
        assertThat(tokenGenerator.getUsername(accessToken)).isEqualTo(username);


    }
}