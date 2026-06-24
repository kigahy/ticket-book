package com.github.kigahy.ticketbook.member.repository;

import com.github.kigahy.ticketbook.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
//import java.lang.reflect.Member;

// Repository는 DB 접근용. JpaRepo를 상속받았으므로 비워둬도 돌아감
public interface MemberRepository extends JpaRepository<Member, Long> {

    // 이메일 중복검사. "조회용"
    boolean existsByEmail(String email);

    // 로그인용. email기준으로 member타입으로 조회
    Optional<Member> findByEmail(String Email);
}