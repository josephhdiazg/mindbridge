package com.usta.mindbridge.service.impl;

import com.usta.mindbridge.dto.request.LoginRequest;
import com.usta.mindbridge.dto.request.RegisterRequest;
import com.usta.mindbridge.dto.response.AuthResponse;
import com.usta.mindbridge.dto.response.UserResponse;
import com.usta.mindbridge.model.User;
import com.usta.mindbridge.exception.custom.DuplicateResourceException;
import com.usta.mindbridge.mapper.UserMapper;
import com.usta.mindbridge.repository.UserRepository;
import com.usta.mindbridge.security.JwtService;
import com.usta.mindbridge.service.AuthService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthServiceImpl(UserRepository userRepository,
                           UserMapper userMapper,
                           PasswordEncoder passwordEncoder,
                           JwtService jwtService,
                           AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    @Override
    public UserResponse register(RegisterRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new DuplicateResourceException("User", "email", request.getEmail());
        }
        User user = userMapper.toEntity(request, passwordEncoder.encode(request.getPassword()));
        return userMapper.toResponse(userRepository.save(user));
    }

    @Override
    public AuthResponse login(LoginRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow();
        String token = jwtService.generateToken(user);
        return AuthResponse.builder()
                .token(token)
                .expiresIn(jwtService.getExpiration())
                .role(user.getRole().name())
                .build();
    }
}