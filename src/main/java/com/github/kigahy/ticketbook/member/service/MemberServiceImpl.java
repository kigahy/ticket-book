package com.github.kigahy.ticketbook.member.service;


import com.github.kigahy.ticketbook.member.dto.request.SignupRequest;
import com.github.kigahy.ticketbook.member.entity.Member;
import com.github.kigahy.ticketbook.member.repository.MemberRepository;
//import com.github.kigahy.ticketbook.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    // 객체 바꿔치기, 갈취 방지하기 위한 final 생성자. 멀티스레드 환경에서도 안전성 위함
    private final MemberRepository memberRepository;

    @Override
    public void signup(SignupRequest request) {
        if(memberRepository.existsByEmail(request.getEmail())) {
            throw new IllegalArgumentException("이미 가입된 이메일입니다.");
        }

        Member member = new Member(
                request.getEmail(),
                request.getPassword(),
                request.getName()
        );

        memberRepository.save(member);
    }
}