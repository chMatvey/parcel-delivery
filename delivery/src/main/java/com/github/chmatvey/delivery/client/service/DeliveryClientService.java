package com.github.chmatvey.delivery.client.service;

import com.github.chmatvey.delivery.client.dto.DeliveryDetails;

public interface DeliveryClientService {
    DeliveryDetails getDeliveryDetails(long orderId, long clientId);

    void cancelDelivery(long orderId);
}
