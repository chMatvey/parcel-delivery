package com.github.chmatvey.order.client.dto;

import com.github.chmatvey.order.common.entity.Order;
import lombok.Builder;

@Builder
public record OrderDetailsResponse(
        long orderId,
        String details,
        String origin,
        String destination
        // todo delivery fields
) {
    public static OrderDetailsResponse create(Order order) {
        return OrderDetailsResponse.builder()
                .orderId(order.getId())
                .details(order.getDetails())
                .origin(order.getOrigin())
                .destination(order.getDestination())
                .build();
    }
}
