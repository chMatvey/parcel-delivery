package com.github.chmatvey.delivery.courier.web;

import com.github.chmatvey.delivery.courier.dto.DeliveryDetailsResponse;
import com.github.chmatvey.delivery.courier.dto.DeliveryDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

@Tag(name = "Delivery Courier API")
public interface DeliveryCourierController {
    @Operation(description = "Accept delivery")
    void acceptDelivery(long orderId, long courierId);

    @Operation(description = "Complete delivery")
    void completeDelivery(long orderId, long courierId);

    @Operation(description = "Get delivery details")
    DeliveryDetailsResponse getDeliveryDetails(long orderId, long courierId);

    @Operation(description = "Get courier deliveries")
    List<DeliveryDto> getCourierDeliveries(long courierId);
}
