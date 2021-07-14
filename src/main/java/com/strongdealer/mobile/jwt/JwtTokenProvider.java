package com.strongdealer.mobile.jwt;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.util.Base64;
import java.util.Date;

/*
토큰을 생성하고 검증하는 컴포넌트
실제로 이 컴포넌트를 이용하는것은 인증작업을 진행하는 Filter클래스
Filter는 검증이 끝난 JWT로부터 유저정보를 받아와서 UsernamePasswordAuthenticationFilter로 전달 */
@RequiredArgsConstructor
@Component
public class JwtTokenProvider {

    // Security UserDetailService
    private final UserDetailsService userDetailsService;

    // 보호키
    private String secretKey = "STRONG_DEALDER";

    //토큰 지속시간 30분
    private static final long TokenValidTime = 30 * 60 * 1000L;


    //객체초기화, secretKey를 Base64로 인코딩한다.
    @PostConstruct
    protected void init(){
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
    }

    //JWT토큰생성
    public String createToken(String username) {
        Claims claims = Jwts.claims();
        claims.put("username", username);
        Date now = new Date();
        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + TokenValidTime))
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();

    }

    //JWT토큰에서 인증정보 조회
    public Authentication getAuthentication(String token){
        // 토큰에서 추출한 회원정보를 받아 유저를 로드하고 이를 userDetails에 담아 UsernamePasswordAuthenticationToken에 담아보낸다.
        UserDetails userDetails = userDetailsService.loadUserByUsername(this.getUsername(token));
        // UsernamePasswordAuthenticationToken - Authentication의 구현체
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    //토큰에서 회원정보(username) 추출
    public String getUsername(String token){
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject();
    }

    // HTTP요청헤더에서 token추출
    public String getToken(HttpServletRequest request) {
        return request.getHeader("Authorization");

    }

    // 토큰 만료일자 확인
    public boolean validateToken(String token) {
        try {
            Jws<Claims> claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
            return !claims.getBody().getExpiration().before(new Date());
        } catch (Exception e) {
            return false;
        }
    }
}
