package com.strongdealer.mobile.jwt;

// 토큰 생성 & 검증, 인증작업을 진행할 Filter클래스에서 이용할 클래스

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@RequiredArgsConstructor
@Component
public class JwtTokenGenerator{



    // 보호키
    private String secretKey = "STRONG_DEALDER";

    //토큰 지속시간 30분
    private static final long TokenValidTime = 30 * 60 * 1000L;


    // 토큰에서 Claim정보 (사용자username)추출
    public String getUsername(String token) {
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject();
    }


    // HTTP요청헤더에서 token추출
    public String getToken(HttpServletRequest request) {
        return request.getHeader("Authorization");

    }

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
    public Boolean verifyToken(String token) {
        try {
            Jws<Claims> claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
            return !claims.getBody().getExpiration().before(new Date());
        } catch (Exception e) {
            return false;
        }

    }
}
