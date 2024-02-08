package com.github.chmatvey.delivery.courier.dto;

import com.github.chmatvey.delivery.common.entity.Delivery;
import com.github.chmatvey.delivery.common.entity.DeliveryStatus;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record DeliveryDetailsResponse(
        DeliveryStatus status,
        String sourceAddress,
        String deliveryAddress,
        LocalDateTime updatedAt
) {
    public static DeliveryDetailsResponse create(Delivery delivery) {
        return DeliveryDetailsResponse.builder()
                .status(delivery.getStatus())
                .sourceAddress(delivery.getSourceAddress())
                .deliveryAddress(delivery.getDeliveryAddress())
                .updatedAt(delivery.getUpdatedAt())
                .build();
    }
}
