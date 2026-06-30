package com.github.kigahy.ticketbook.member.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

// DTO는 계층간 데이터 전달용 객체.
@Getter
@NoArgsConstructor
public class SignupRequest {

    // 회원들한테 정보 받아와야 함
    private String email;
    private String password;
    private String name;
}
