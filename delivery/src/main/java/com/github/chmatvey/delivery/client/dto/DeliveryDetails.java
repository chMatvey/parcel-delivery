package com.github.chmatvey.delivery.client.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.github.chmatvey.delivery.common.entity.Delivery;
import com.github.chmatvey.delivery.common.entity.DeliveryStatus;
import jakarta.annotation.Nullable;
import lombok.Builder;
import org.springframework.data.geo.Point;

import java.time.LocalDateTime;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@JsonInclude(NON_NULL)
@Builder
public record DeliveryDetails(
        DeliveryStatus status,
        @Nullable
        LocalDateTime deliveryDate
) {
        public static DeliveryDetails create(Delivery delivery) {
                return DeliveryDetails.builder()
                        .status(delivery.getStatus())
                        .deliveryDate(delivery.getDeliveryDate())
                        .build();
        }
}
