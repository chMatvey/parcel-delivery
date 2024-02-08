package com.github.chmatvey.user.web.controller;

import com.github.chmatvey.user.dto.CourierResponse;
import com.github.chmatvey.user.dto.CourierStatusSetRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

@Tag(name = "Courier API")
public interface CourierController {
    @Operation(description = "Get all couriers")
    List<CourierResponse> couriers();

    @Operation(description = "Set status")
    void setStatus(CourierStatusSetRequest request, long courierId);
}
