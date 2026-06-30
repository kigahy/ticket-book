package com.github.kigahy.ticketbook.auth.service;

import com.github.kigahy.ticketbook.auth.dto.request.LoginRequest;
import com.github.kigahy.ticketbook.auth.dto.response.LoginResponse;

public interface AuthService {

    LoginResponse login(LoginRequest request);
}
