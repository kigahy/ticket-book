package com.github.kigahy.ticketbook.global.security;

import com.github.kigahy.ticketbook.member.entity.Member;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.Date;
//import java.security.Key; 대신 밑 javax 사용
import javax.crypto.SecretKey;
import io.jsonwebtoken.security.Keys;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtUtil {

    private final JwtProperties jwtProperties;

    // secretKey 생성 메서드
    private SecretKey getAuthKey() {
        byte[] keyBytes = jwtProperties.getSecret().getBytes(StandardCharsets.UTF_8);
        return Keys.hmacShaKeyFor(keyBytes);
    }


    // jwt 토큰 생성 메서드
    public String generateAccessToken(Member member) {

        Date now = new Date();
        Date expiration = new Date(now.getTime() + jwtProperties.getExpirationTime());

        // 헤더는 개인 세팅 안하고 signwith로 자동으로 만듦
        return Jwts.builder()
                .subject(String.valueOf(member.getId()))
                .claim("email", member.getEmail())
                .claim("role", member.getRole())
                .issuedAt(now)
                .expiration(expiration)
                .signWith(getAuthKey())
                .compact();
    }
    // jwt인증과정 추가했으니 AuthServiceImpl에 accessToken 추가해줌
}
