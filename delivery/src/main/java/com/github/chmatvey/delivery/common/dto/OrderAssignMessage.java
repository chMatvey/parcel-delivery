package com.github.chmatvey.delivery.common.dto;

import lombok.Builder;

@Builder
public record OrderAssignMessage(
        long orderId,
        long clientId,
        long courierId,
        String origin,
        String destination
) {
}
