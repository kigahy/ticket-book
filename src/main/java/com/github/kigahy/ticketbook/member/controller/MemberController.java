package com.github.kigahy.ticketbook.member.controller;

import com.github.kigahy.ticketbook.member.dto.request.SignupRequest;
import com.github.kigahy.ticketbook.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/members")
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/signup")
    @ResponseStatus(HttpStatus.CREATED)
    public void signup(@RequestBody SignupRequest request) {
        memberService.signup(request);
    }
}
