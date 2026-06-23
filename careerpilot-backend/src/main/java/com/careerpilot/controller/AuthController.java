package com.careerpilot.controller;

import com.careerpilot.dto.RegisterRequest;
import com.careerpilot.entity.User;
import com.careerpilot.service.AuthService;
import org.springframework.web.bind.annotation.*;
import com.careerpilot.dto.LoginRequest;
import jakarta.validation.Valid;

import com.careerpilot.dto.LoginResponse;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

        @PostMapping("/register")
        public User register(@Valid @RequestBody RegisterRequest request) {
            return authService.register(request);
        }
    @GetMapping("/test")
    public String test() {
        return "Auth Controller Working";
    }
    @GetMapping("/hello")
    public String hello() {
        return "Auth Module Working";
    }
    @PostMapping("/login")
    public String login(@RequestBody LoginRequest request) {

        return authService.login(
                request.getEmail(),
                request.getPassword()
        );
    }

    @PostMapping("/jwt-login")
    public LoginResponse jwtLogin(
            @RequestBody LoginRequest request) {

        String token =
                authService.loginAndGenerateToken(
                        request.getEmail(),
                        request.getPassword());

        return new LoginResponse(token);
    }
}