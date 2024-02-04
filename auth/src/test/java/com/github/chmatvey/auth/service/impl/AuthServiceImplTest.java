package com.github.chmatvey.auth.service.impl;

import com.github.chmatvey.auth.client.UserClient;
import com.github.chmatvey.auth.client.dto.UserLogInResponse;
import com.github.chmatvey.auth.dto.LogInRequest;
import com.github.chmatvey.auth.dto.LogInResponse;
import com.github.chmatvey.auth.dto.UserRegisterRequest;
import com.github.chmatvey.auth.service.PasswordService;
import com.github.chmatvey.auth.service.error.IncorrectPasswordException;
import com.github.chmatvey.jwt.service.JwtService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = AuthServiceImpl.class)
class AuthServiceImplTest {
    @Autowired
    AuthServiceImpl authService;

    @MockBean
    UserClient userClient;

    @MockBean
    PasswordService passwordService;

    @MockBean
    JwtService jwtService;

    @Test
    void register() {
        // Given
        UserRegisterRequest request = UserRegisterRequest.builder()
                .login("alex_123")
                .password("xpFbWK573e")
                .build();
        when(passwordService.hashPassword(request.password()))
                .thenReturn("hashedPassword");

        // When
        authService.registerClient(request);

        // Then
        verify(userClient).create(UserRegisterRequest.builder()
                .login(request.login())
                .password("hashedPassword")
                .build());
    }

    @Test
    void logInSuccessfully() {
        // Given
        LogInRequest request = LogInRequest.builder()
                .login("user_040224")
                .password("Avz54DWhCc")
                .build();
        UserLogInResponse logInResponse = UserLogInResponse.builder()
                .userId(40224)
                .role("CLIENT")
                .password("hashedPassword")
                .build();

        when(userClient.logInInfo(any()))
                .thenReturn(logInResponse);
        when(passwordService.checkPassword(request.password(), logInResponse.password()))
                .thenReturn(true);
        when(jwtService.generateToken(logInResponse.toUserInfo()))
                .thenReturn("token");

        // When
        LogInResponse response = authService.logIn(request);

        // Then
        assertEquals("token", response.token());
    }

    @Test
    void loginWithIncorrectPassword() {
        // Given
        LogInRequest request = LogInRequest.builder()
                .login("user_040224")
                .password("incorrectPassword")
                .build();
        UserLogInResponse logInResponse = UserLogInResponse.builder()
                .userId(40224)
                .role("CLIENT")
                .password("hashedPassword")
                .build();

        when(userClient.logInInfo(any()))
                .thenReturn(logInResponse);
        when(passwordService.checkPassword(request.password(), logInResponse.password()))
                .thenReturn(false);

        // Then
        assertThrows(IncorrectPasswordException.class, () -> authService.logIn(request));
    }
}