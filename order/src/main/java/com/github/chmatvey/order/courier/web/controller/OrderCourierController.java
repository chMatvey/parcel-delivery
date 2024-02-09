package com.github.chmatvey.order.courier.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Order courier API")
public interface OrderCourierController {
    @Operation(description = "Accept order")
    void acceptOrder(long orderId);
}
