package com.github.chmatvey.delivery.common.dto;

import com.github.chmatvey.delivery.common.entity.Delivery;

public record DeliveryAcceptMessage(long orderId) {
    public static DeliveryAcceptMessage create(Delivery delivery) {
        return new DeliveryAcceptMessage(delivery.getOrderId());
    }
}
