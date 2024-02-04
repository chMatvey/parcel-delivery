package com.github.chmatvey.auth.web.controller.impl;

import com.github.chmatvey.auth.dto.LogInRequest;
import com.github.chmatvey.auth.dto.LogInResponse;
import com.github.chmatvey.auth.dto.UserRegisterRequest;
import com.github.chmatvey.auth.service.AuthService;
import com.github.chmatvey.auth.web.controller.AuthController;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/api/public/v1/auth")
@RequiredArgsConstructor
public class AuthControllerImpl implements AuthController {
    private final AuthService authService;

    @PostMapping("/register")
    @ResponseStatus(CREATED)
    @Override
    public void register(@RequestBody UserRegisterRequest request) {
        authService.registerClient(request);
    }

    @PostMapping("/log-in")
    @Override
    public LogInResponse logIn(@RequestBody LogInRequest request) {
        return authService.logIn(request);
    }
}
