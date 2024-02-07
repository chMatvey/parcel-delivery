package com.github.chmatvey.order.client.web.controller;

import com.github.chmatvey.order.client.dto.*;
import com.github.chmatvey.order.client.service.OrderClientService;
import com.github.chmatvey.order.client.web.controller.OrderClientController;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/api/v1/order/client")
@RequiredArgsConstructor
public class OrderClientControllerImpl implements OrderClientController {
    private final OrderClientService orderService;

    @PostMapping
    @ResponseStatus(CREATED)
    @Override
    public OrderCreateResponse createOrder(@RequestBody OrderCreateRequest request,
                                           @RequestHeader("User-Id") long userId) {
        return orderService.createOrder(request, userId);
    }

    @GetMapping("/{orderId}/details")
    @Override
    public OrderDetailsResponse getOrderDetails(@PathVariable long orderId,
                                                @RequestHeader("User-Id") long userId) {
        return orderService.getOrderDetails(orderId, userId);
    }

    @PutMapping("/{orderId}/destination")
    @Override
    public void changeOrderDestination(@RequestBody OrderChangeDestinationRequest request,
                                       @PathVariable long orderId,
                                       @RequestHeader("User-Id") long userId) {
        orderService.changeOrderDestination(request, orderId, userId);
    }

    @DeleteMapping("/{orderId}")
    @Override
    public void cancelOrder(@PathVariable long orderId,
                            @RequestHeader("User-Id") long userId) {
        orderService.cancelOrder(orderId, userId);
    }

    @GetMapping
    @Override
    public List<OrderResponse> getAllOrders(@RequestHeader("User-Id") long userId) {
        return orderService.getAllOrders(userId);
    }
}
