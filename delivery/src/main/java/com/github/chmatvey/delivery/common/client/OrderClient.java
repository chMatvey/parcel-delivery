package com.github.chmatvey.delivery.common.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "OrderClient", url = "${integration.order.url}")
public interface OrderClient {
    @PostMapping("/courier/{orderId}")
    void acceptOrder(@PathVariable("orderId") long orderId);
}
