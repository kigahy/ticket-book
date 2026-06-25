package com.github.kigahy.ticketbook.global.security;

import com.github.kigahy.ticketbook.member.entity.Member;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
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


}
