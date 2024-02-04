package com.github.chmatvey.auth.web.controller;

import com.github.chmatvey.auth.dto.UserRegisterRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Courier API")
public interface CourierController {
    @Operation(description = "Register courier account")
    void registerCourier(UserRegisterRequest request);
}
