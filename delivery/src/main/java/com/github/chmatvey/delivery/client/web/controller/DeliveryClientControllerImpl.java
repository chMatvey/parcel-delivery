package com.github.chmatvey.delivery.client.web.controller;

import com.github.chmatvey.delivery.client.dto.DeliveryDetails;
import com.github.chmatvey.delivery.client.service.DeliveryClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/delivery/client")
@RequiredArgsConstructor
public class DeliveryClientControllerImpl implements DeliveryClientController {
    private final DeliveryClientService deliveryService;

    @GetMapping("/{orderId}")
    @Override
    public DeliveryDetails getDeliveryDetails(@PathVariable long orderId,
                                              @RequestHeader("User-Id") long clientId) {
        return deliveryService.getDeliveryDetails(orderId, clientId);
    }
}
