package com.github.chmatvey.order.admin.web.controller;

import com.github.chmatvey.order.admin.dto.OrderAssignRequest;
import com.github.chmatvey.order.admin.service.OrderAdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/order/admin")
@RequiredArgsConstructor
public class OrderAdminControllerImpl implements OrderAdminController {
    private final OrderAdminService orderService;

    @PostMapping("/assign")
    @Override
    public void assignOrderToCourier(OrderAssignRequest request) {
        orderService.assignOrderToCourier(request);
    }
}
