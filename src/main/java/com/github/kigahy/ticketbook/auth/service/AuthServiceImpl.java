package com.github.kigahy.ticketbook.auth.service;

import com.github.kigahy.ticketbook.auth.dto.request.LoginRequest;
import com.github.kigahy.ticketbook.auth.dto.response.LoginResponse;
import com.github.kigahy.ticketbook.global.security.JwtUtil;
import com.github.kigahy.ticketbook.member.entity.Member;
import com.github.kigahy.ticketbook.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService{

    // 의존성 주입
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    @Override
    public LoginResponse login(LoginRequest request) {

        // 1. DB에서 조회해온 Member 엔티티 객체
        // 2. findByEmail을 통해 DB에서 사용자 찾아옴
        // 3. Member자료형의 member 객체를 생성함
        Member member = memberRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("가입되지 않은 이메일입니다."));

        if (!passwordEncoder.matches(request.getPassword(), member.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        // JWT인증과정 추가한 다음 return에 엑세스토큰 추가
        String accessToken = jwtUtil.generateAccessToken(member);

        // LoginResponse에 해당하는 member의 정보를 넣음
        // Response클래스는 Id가 아니라 MemberId로 되어있지만 문제없음. Return하는 순서대로 잘 매치 됨
        return new LoginResponse(
                member.getId(),
                member.getEmail(),
                member.getName(),
                accessToken
        );
    }

}
