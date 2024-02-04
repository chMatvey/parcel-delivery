package com.github.chmatvey.auth.service.impl;

import com.github.chmatvey.auth.client.UserClient;
import com.github.chmatvey.auth.client.dto.UserCreateRequest;
import com.github.chmatvey.auth.dto.UserRegisterRequest;
import com.github.chmatvey.auth.service.CourierService;
import com.github.chmatvey.auth.service.PasswordService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.github.chmatvey.auth.client.dto.UserRole.COURIER;

@Service
@RequiredArgsConstructor
public class CourierServiceImpl implements CourierService {
    private final UserClient userClient;
    private final PasswordService passwordService;

    @Override
    public void registerCourier(UserRegisterRequest request) {
        String hashedPassword = passwordService.hashPassword(request.password());
        userClient.create(UserCreateRequest.builder()
                .login(request.login())
                .password(hashedPassword)
                .role(COURIER)
                .build());
    }
}
