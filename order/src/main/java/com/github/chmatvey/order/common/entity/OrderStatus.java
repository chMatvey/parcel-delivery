package com.github.chmatvey.order.common.entity;

public enum OrderStatus {
    /**
     * Order is created and waiting for a courier to be assigned.
     */
    CREATED,

    /**
     * Courier is assigned to the order.
     */
    PICKUP_ASSIGNED,

    /**
     * 	The order has been cancelled
     */
    CANCELED,

    /**
     * Courier is accepted the order.
     */
    PICKUP_ACCEPTED,

    /**
     * Order is delivered to the delivery location.
     */
    DELIVERED;
}
