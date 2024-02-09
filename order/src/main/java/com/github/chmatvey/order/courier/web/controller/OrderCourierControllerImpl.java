package com.github.chmatvey.order.courier.web.controller;

import com.github.chmatvey.order.courier.service.OrderCourierService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/internal/v1/order/courier")
@RequiredArgsConstructor
public class OrderCourierControllerImpl implements OrderCourierController {
    private final OrderCourierService orderService;

    @PostMapping("{orderId}")
    @Override
    public void acceptOrder(@PathVariable long orderId) {
        orderService.acceptOrder(orderId);
    }
}
