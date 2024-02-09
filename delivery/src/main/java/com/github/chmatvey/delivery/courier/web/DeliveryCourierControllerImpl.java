package com.github.chmatvey.delivery.courier.web;

import com.github.chmatvey.delivery.courier.dto.DeliveryDetailsResponse;
import com.github.chmatvey.delivery.courier.dto.DeliveryDto;
import com.github.chmatvey.delivery.courier.service.DeliveryCourierService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/delivery/courier")
@RequiredArgsConstructor
public class DeliveryCourierControllerImpl implements DeliveryCourierController {
    private final DeliveryCourierService deliveryService;

    @PostMapping("/{orderId}/accept")
    @Override
    public void acceptDelivery(@PathVariable long orderId,
                               @RequestHeader("User-Id") long courierId) {
        deliveryService.acceptDelivery(orderId, courierId);
    }

    @PostMapping("/{orderId}/complete")
    @Override
    public void completeDelivery(@PathVariable long orderId,
                                 @RequestHeader("User-Id") long courierId) {
        deliveryService.completeDelivery(orderId, courierId);
    }

    @GetMapping("/{orderId}/details")
    @Override
    public DeliveryDetailsResponse getDeliveryDetails(@PathVariable long orderId,
                                                      @RequestHeader("User-Id") long courierId) {
        return deliveryService.getDeliveryDetails(orderId, courierId);
    }

    @GetMapping
    @Override
    public List<DeliveryDto> getCourierDeliveries(@RequestHeader("User-Id") long courierId) {
        return deliveryService.getCourierDeliveries(courierId);
    }
}
