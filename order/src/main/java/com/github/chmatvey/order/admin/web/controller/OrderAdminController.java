package com.github.chmatvey.order.admin.web.controller;

import com.github.chmatvey.order.admin.dto.OrderAssignRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Order Admin API")
public interface OrderAdminController {
    @Operation(description = "Assing order to courier")
    void assignOrderToCourier(OrderAssignRequest request);
}
