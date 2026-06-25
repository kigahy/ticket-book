package com.github.kigahy.ticketbook.global.security;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component

// 1. 이 어노테이션이 평범한 자바 클래스를 yaml에 있는 jwt를 찾아내고,
@ConfigurationProperties(prefix = "jwt")
public class JwtProperties {

    // secret은 일개 변수가 아니라 jwt그룹 안의 secret 키 및 만료시간을 찾아냄
    private String secret;
    private Long expirationTime;
}
