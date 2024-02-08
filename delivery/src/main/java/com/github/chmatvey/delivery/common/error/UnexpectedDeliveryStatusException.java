package com.github.chmatvey.delivery.common.error;

import com.github.chmatvey.delivery.common.entity.DeliveryStatus;

public class UnexpectedDeliveryStatusException extends RuntimeException {
    public UnexpectedDeliveryStatusException(DeliveryStatus status) {
        super("Unexpected delivery status = " + status);
    }
}
