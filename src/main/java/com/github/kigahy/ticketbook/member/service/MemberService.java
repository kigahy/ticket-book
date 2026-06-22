package com.github.kigahy.ticketbook.member.service;

// 계층 간 연결해주는 DTO를 import
import com.github.kigahy.ticketbook.member.dto.request.SignupRequest;

public interface MemberService {
    void signup(SignupRequest request);
}
