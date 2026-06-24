package com.github.kigahy.ticketbook.auth.controller;

import com.github.kigahy.ticketbook.auth.dto.request.LoginRequest;
import com.github.kigahy.ticketbook.auth.dto.response.LoginResponse;
import com.github.kigahy.ticketbook.auth.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
        LoginResponse response = authService.login(request);

        return ResponseEntity.ok(response);
    }
}
