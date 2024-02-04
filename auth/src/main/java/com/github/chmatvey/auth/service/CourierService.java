package com.github.chmatvey.auth.service;

import com.github.chmatvey.auth.dto.UserRegisterRequest;

public interface CourierService {
    void registerCourier(UserRegisterRequest request);
}
