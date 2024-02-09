package com.github.chmatvey.delivery.common.dto;

import com.github.chmatvey.delivery.common.entity.Delivery;

public record DeliveryCompleteMessage(long orderId) {
    public static DeliveryCompleteMessage create(Delivery delivery) {
        return new DeliveryCompleteMessage(delivery.getOrderId());
    }
}
