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
     * Courier is accepted the order and is on the way to the pickup location.
     */
    PICKUP_ACCEPTED,

    /**
     * Courier collected the order and is on the way to the delivery location.
     */
    PROCESSING,

    /**
     * Order is delivered to the delivery location.
     */
    DELIVERED;
}
