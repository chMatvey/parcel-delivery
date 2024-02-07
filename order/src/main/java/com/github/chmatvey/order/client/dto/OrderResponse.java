package com.github.chmatvey.order.client.dto;

import com.github.chmatvey.order.common.entity.Order;
import lombok.Builder;

@Builder
public record OrderResponse(
        long orderId,
        String details,
        String origin,
        String destination
) {
    public static OrderResponse create(Order order) {
        return OrderResponse.builder()
                .orderId(order.getId())
                .details(order.getDetails())
                .origin(order.getOrigin())
                .destination(order.getDestination())
                .build();
    }
}
