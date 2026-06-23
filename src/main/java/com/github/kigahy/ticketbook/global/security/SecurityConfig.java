package com.github.kigahy.ticketbook.global.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable()) // Postman JSON 요청 위해 CSRF 임시 비활성화
            .formLogin(form -> form.disable()) // 폼 로그인 끄기
            .httpBasic(httpBasic -> httpBasic.disable()) // Basic 로그인 끄기
            .authorizeHttpRequests(auth -> auth
                    .requestMatchers("/api/members/signup").permitAll() // 회원가입 API 허용
                    .anyRequest().permitAll() // 나머지도 개발 중이라 일단 허용
            /*
            공식문서 내용
            (모든 요청은 로그인 필요, HTTP Basic 로그인 허용, 폼 로그인 화면 허용 => 내 회원가입 API 막힘)
            .authorizeHttpRequests((authorize) -> authorize
                .anyRequest().authenticated()
            )
            .httpBasic(withDefaults())
            .formLogin(withDefaults());
            */
            );
        return http.build();
    }
}

