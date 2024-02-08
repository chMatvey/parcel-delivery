package com.github.chmatvey.delivery.courier.service;

import com.github.chmatvey.delivery.courier.dto.DeliveryDetailsResponse;
import com.github.chmatvey.delivery.courier.dto.DeliveryDto;

import java.util.List;

public interface DeliveryCourierService {
    void acceptDelivery(long orderId, long courierId);

    void completeDelivery(long orderId, long courierId);

    DeliveryDetailsResponse getDeliveryDetails(long orderId, long courierId);

    List<DeliveryDto> getCourierDeliveries(long courierId);
}
