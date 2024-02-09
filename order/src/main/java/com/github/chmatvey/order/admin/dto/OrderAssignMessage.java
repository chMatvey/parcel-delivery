package com.github.chmatvey.order.admin.dto;

import com.github.chmatvey.order.common.entity.Order;
import lombok.Builder;

@Builder
public record OrderAssignMessage(
        long orderId,
        long clientId,
        long courierId,
        String origin,
        String destination
) {
    public static OrderAssignMessage create(Order order) {
        return OrderAssignMessage.builder()
                .orderId(order.getId())
                .clientId(order.getUserId())
                .courierId(order.getCourierId())
                .origin(order.getOrigin())
                .destination(order.getDestination())
                .build();
    }
}
