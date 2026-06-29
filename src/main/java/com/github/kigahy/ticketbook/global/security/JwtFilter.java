package com.github.kigahy.ticketbook.global.security;


import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;

@Component
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {
    // Http 요청시 Controller에 도착하기 전 미리 검사하는 역할의 클래스.

    // 요청에 들어오는 헤더와 bearer. 이건 jwt에서 제공하는 기본 기능임. 바꿔지 않음
    private static final String AUTHORIZATION_HEADER = "Authorization";
    private static final String BEARER_PREFIX = "Bearer";

    // jwt유틸 의존성 주입도
    private final JwtUtil jwtUtil;

    // 여기서부터 서블릿에서 제공하는 인터페이스가 많이 나옴
    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {

        // HTTP 요청에서 헤더 읽어옴
        String authorizationHeader = request.getHeader(AUTHORIZATION_HEADER);

        // HTTP요청이 인증헤더와 Bearer이라면 엑세스토큰에 담아둠
        if (authorizationHeader != null && authorizationHeader.startsWith(BEARER_PREFIX)) {
            String accessToken = authorizationHeader.substring(BEARER_PREFIX.length());

            // jwtUtil파일에서 토큰이 참거짓으로 판별된다면 사용자정보 받아옴
            if (jwtUtil.validateToken(accessToken)) {
                Long memberId = jwtUtil.getmemberIdFromToken(accessToken);

                UsernamePasswordAuthenticationToken authentication =
                    new UsernamePasswordAuthenticationToken(
                        memberId,
                        null,
                        Collections.emptyList()
                );

                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }

        filterChain.doFilter(request, response);
    }
}
