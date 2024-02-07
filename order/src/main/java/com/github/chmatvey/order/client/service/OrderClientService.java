package com.github.chmatvey.order.client.service;

import com.github.chmatvey.order.client.dto.*;

import java.util.List;

public interface OrderClientService {
    OrderCreateResponse createOrder(OrderCreateRequest request, long userId);

    OrderDetailsResponse getOrderDetails(long orderId, long userId);

    void changeOrderDestination(OrderChangeDestinationRequest request, long orderId, long userId);

    void cancelOrder(long orderId, long userId);

    List<OrderResponse> getAllOrders(long userId);
}
