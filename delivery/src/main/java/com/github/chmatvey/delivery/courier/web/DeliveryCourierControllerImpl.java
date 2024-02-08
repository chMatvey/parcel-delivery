package com.github.chmatvey.delivery.courier.web;

import com.github.chmatvey.delivery.courier.dto.DeliveryDetailsResponse;
import com.github.chmatvey.delivery.courier.dto.DeliveryDto;
import com.github.chmatvey.delivery.courier.service.DeliveryCourierService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class DeliveryCourierControllerImpl implements DeliveryCourierController {
    private final DeliveryCourierService deliveryService;

    @Override
    public void acceptDelivery(long orderId, long courierId) {
        deliveryService.acceptDelivery(orderId, courierId);
    }

    @Override
    public void completeDelivery(long orderId, long courierId) {
        deliveryService.completeDelivery(orderId, courierId);
    }

    @Override
    public DeliveryDetailsResponse getDeliveryDetails(long orderId, long courierId) {
        return deliveryService.getDeliveryDetails(orderId, courierId);
    }

    @Override
    public List<DeliveryDto> getCourierDeliveries(long courierId) {
        return deliveryService.getCourierDeliveries(courierId);
    }
}
