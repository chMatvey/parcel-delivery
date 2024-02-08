package com.github.chmatvey.delivery.client.web.controller;

import com.github.chmatvey.delivery.client.dto.DeliveryDetails;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Delivery Client API")
public interface DeliveryClientController {
    @Operation(description = "Get delivery details")
    DeliveryDetails getDeliveryDetails(long orderId, long clientId);
}
