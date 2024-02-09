package com.github.chmatvey.order.common.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "DeliveryClient", url = "${integration.delivery.url}")
public interface DeliveryClient {
    @DeleteMapping("/client/{orderId}")
    void cancelDelivery(@PathVariable("orderId") long orderId);
}
