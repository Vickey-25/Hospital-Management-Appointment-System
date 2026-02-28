package com.hospital.controller;

import com.hospital.dto.*;
import com.hospital.entity.*;
import com.hospital.repository.*;
import com.hospital.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    @PostMapping("/register")
    public String register(@RequestBody RegisterRequest request) {

        Role role = roleRepository.findByName("PATIENT")
                .orElseThrow(() -> new RuntimeException("Role not found"));

        User user = User.builder()
                .id(UUID.randomUUID().toString())
                .fullName(request.getFullName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .isActive(true)
                .role(role)
                .build();

        userRepository.save(user);

        return "User Registered Successfully";
    }

    @PostMapping("/login")
    public AuthResponse login(@RequestBody LoginRequest request) {

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }

        String token = jwtUtil.generateToken(
        user.getEmail(),
        user.getRole().getName()
);

        return new AuthResponse(token);
    }
}