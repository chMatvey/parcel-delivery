package com.github.chmatvey.user.web.controller.impl;

import com.github.chmatvey.user.dto.CourierResponse;
import com.github.chmatvey.user.dto.CourierStatusSetRequest;
import com.github.chmatvey.user.service.CourierService;
import com.github.chmatvey.user.web.controller.CourierController;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user/courier")
@RequiredArgsConstructor
public class CourierControllerImpl implements CourierController {
    private final CourierService courierService;

    @GetMapping
    @Override
    public List<CourierResponse> couriers() {
        return courierService.getAllCouriers();
    }

    @PutMapping
    @Override
    public void setStatus(@RequestBody CourierStatusSetRequest request,
                          @RequestHeader("User-Id") long courierId) {
        courierService.setStatus(request, courierId);
    }
}
