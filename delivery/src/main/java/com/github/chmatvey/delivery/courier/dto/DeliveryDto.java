package com.github.chmatvey.delivery.courier.dto;

import com.github.chmatvey.delivery.common.entity.DeliveryStatus;

import java.time.LocalDateTime;

public record DeliveryDto(
        long orderId,
        DeliveryStatus status,
        LocalDateTime updatedAt
) {
}
