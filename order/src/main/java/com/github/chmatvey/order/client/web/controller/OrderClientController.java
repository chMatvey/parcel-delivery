package com.github.chmatvey.order.client.web.controller;

import com.github.chmatvey.order.client.dto.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

@Tag(name = "Order client API")
public interface OrderClientController {
    @Operation(description = "Create a new order")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Order successfully created"),
    })
    OrderCreateResponse createOrder(OrderCreateRequest request, long userId);

    @Operation(description = "Get an order by id")
    OrderDetailsResponse getOrderDetails(long orderId, long userId);

    @Operation(description = "Change order destination")
    void changeOrderDestination(OrderChangeDestinationRequest request, long orderId, long userId);

    @Operation(description = "Cancel order")
    void cancelOrder(long orderId, long userId);

    @Operation(description = "Get all orders")
    List<OrderResponse> getAllOrders(long userId);
}
