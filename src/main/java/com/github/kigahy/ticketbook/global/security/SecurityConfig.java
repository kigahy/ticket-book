package com.github.kigahy.ticketbook.global.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtFilter jwtFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable()) // Postman JSON 요청 위해 CSRF 임시 비활성화
            .formLogin(form -> form.disable()) // 폼 로그인 끄기
            .httpBasic(httpBasic -> httpBasic.disable()) // Basic 로그인 끄기
            .authorizeHttpRequests(auth -> auth
                    .requestMatchers("/api/members/signup", "/api/auth/login").permitAll() // 회원가입,로그인 API 허용
//                    .anyRequest().permitAll() // 나머지도 개발 중이라 일단 허용
                    .anyRequest().authenticated() // permitall아닌 authenticated로 수정
            /*
            공식문서 내용
            (모든 요청은 로그인 필요, HTTP Basic 로그인 허용, 폼 로그인 화면 허용 => 내 회원가입 API 막힘)
            .authorizeHttpRequests((authorize) -> authorize
                .anyRequest().authenticated()
            )
            .httpBasic(withDefaults())
            .formLogin(withDefaults());
            */
            )
            // jwt필터로직 추가하고 설정도 추가
            .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


//    @Bean
//    public JwtFilter jwtFilter() { return new } 이게 아님. 단지 의존성 주입하고 설정에 추가하는 것
}

