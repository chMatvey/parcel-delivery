package com.github.chmatvey.order.admin.service;

import com.github.chmatvey.order.admin.dto.OrderAssignRequest;

public interface OrderAdminService {
    void assignOrderToCourier(OrderAssignRequest request);
}
