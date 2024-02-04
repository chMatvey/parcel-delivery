package com.github.chmatvey.auth.service.impl;

import com.github.chmatvey.auth.client.UserClient;
import com.github.chmatvey.auth.client.dto.UserCreateRequest;
import com.github.chmatvey.auth.client.dto.UserLogInRequest;
import com.github.chmatvey.auth.client.dto.UserLogInResponse;
import com.github.chmatvey.auth.dto.LogInRequest;
import com.github.chmatvey.auth.dto.LogInResponse;
import com.github.chmatvey.auth.dto.UserRegisterRequest;
import com.github.chmatvey.auth.service.AuthService;
import com.github.chmatvey.auth.service.PasswordService;
import com.github.chmatvey.auth.service.error.IncorrectPasswordException;
import com.github.chmatvey.jwt.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.github.chmatvey.auth.client.dto.UserRole.CLIENT;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserClient userClient;
    private final PasswordService passwordService;
    private final JwtService jwtService;

    @Override
    public void registerClient(UserRegisterRequest request) {
        String hashedPassword = passwordService.hashPassword(request.password());
        userClient.create(UserCreateRequest.builder()
                .login(request.login())
                .password(hashedPassword)
                .role(CLIENT)
                .build());
    }

    @Override
    public LogInResponse logIn(LogInRequest request) {
        UserLogInResponse response = userClient.logInInfo(UserLogInRequest.create(request));

        if (passwordService.checkPassword(request.password(), response.password())) {
            String token = jwtService.generateToken(response.toUserInfo());
            return new LogInResponse(token);
        } else {
            throw new IncorrectPasswordException();
        }
    }
}
