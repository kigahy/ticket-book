package com.github.kigahy.ticketbook.auth.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class LoginResponse {

    // DB 속성값과 달라도 되며, 사용자에게 받아올 변수 선언함
    // 그러므로 import로 member패키지의 엔티티를 안 적어둬도 되는 것

    private Long memberId;
    private String email;
    private String name;
}
