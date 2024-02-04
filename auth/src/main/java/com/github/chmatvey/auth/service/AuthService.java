package com.github.chmatvey.auth.service;

import com.github.chmatvey.auth.dto.LogInRequest;
import com.github.chmatvey.auth.dto.LogInResponse;
import com.github.chmatvey.auth.dto.UserRegisterRequest;
import org.springframework.stereotype.Service;

@Service
public interface AuthService {
    void registerClient(UserRegisterRequest request);

    LogInResponse logIn(LogInRequest request);
}
