package com.github.chmatvey.auth.web.controller.impl;

import com.github.chmatvey.auth.dto.UserRegisterRequest;
import com.github.chmatvey.auth.service.CourierService;
import com.github.chmatvey.auth.web.controller.CourierController;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth/courier")
@RequiredArgsConstructor
public class CourierControllerImpl implements CourierController {
    private final CourierService courierService;

    @PostMapping("/register")
    @Override
    public void registerCourier(@RequestBody UserRegisterRequest request) {
        courierService.registerCourier(request);
    }
}
