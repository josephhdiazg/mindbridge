package com.usta.mindbridge.service;

import com.usta.mindbridge.dto.request.LoginRequest;
import com.usta.mindbridge.dto.request.RegisterRequest;
import com.usta.mindbridge.dto.response.AuthResponse;
import com.usta.mindbridge.dto.response.UserResponse;

public interface AuthService {
    UserResponse register(RegisterRequest request);
    AuthResponse login(LoginRequest request);
}