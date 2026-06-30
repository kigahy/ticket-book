package com.github.kigahy.ticketbook.member.service;

import com.github.kigahy.ticketbook.member.dto.request.SignupRequest;
import com.github.kigahy.ticketbook.member.entity.Member;
import com.github.kigahy.ticketbook.member.repository.MemberRepository;
//import com.github.kigahy.ticketbook.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    // 객체 바꿔치기, 갈취 방지하기 위한 final 생성자. 멀티스레드 환경에서도 안전성 위함
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void signup(SignupRequest request) {
        if(memberRepository.existsByEmail(request.getEmail())) {
            throw new IllegalArgumentException("이미 가입된 이메일입니다.");
        }

        String encodedPassword = passwordEncoder.encode(request.getPassword());

        Member member = new Member(
                request.getEmail(),
                encodedPassword,
                request.getName()
        );

        memberRepository.save(member);
    }
}