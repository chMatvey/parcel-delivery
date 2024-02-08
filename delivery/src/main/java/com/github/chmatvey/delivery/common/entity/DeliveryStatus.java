package com.github.chmatvey.delivery.common.entity;

public enum DeliveryStatus {
    /**
     * The delivery has been created and is waiting for a courier to accept it.
     */
    CREATED,

    /**
     * Order has been canceled by client.
     */
    CANCELLED,

    /**
     * Courier is accepted the delivery.
     */
    ACCEPTED,

    /**
     * The delivery has been completed.
     */
    COMPLETED
}
