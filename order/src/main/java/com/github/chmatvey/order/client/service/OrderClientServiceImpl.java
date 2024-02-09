package com.github.chmatvey.order.client.service;

import com.github.chmatvey.order.client.dto.*;
import com.github.chmatvey.order.common.client.DeliveryClient;
import com.github.chmatvey.order.common.entity.Order;
import com.github.chmatvey.order.common.error.OrderNotFoundException;
import com.github.chmatvey.order.common.error.UnexpectedOrderStatusException;
import com.github.chmatvey.order.common.repository.OrderRepository;
import jakarta.persistence.PersistenceException;
import lombok.RequiredArgsConstructor;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.github.chmatvey.order.common.entity.OrderStatus.CANCELED;
import static com.github.chmatvey.order.common.entity.OrderStatus.PICKUP_ASSIGNED;

@Service
@RequiredArgsConstructor
public class OrderClientServiceImpl implements OrderClientService {
    private final OrderRepository orderRepository;
    private final DeliveryClient deliveryClient;

    @Override
    public OrderCreateResponse createOrder(OrderCreateRequest request, long userId) {
        Order order = orderRepository.save(Order.builder()
                .userId(userId)
                .details(request.details())
                .origin(request.origin())
                .destination(request.destination())
                .build());

        return new OrderCreateResponse(order.getId());
    }

    @Override
    public OrderDetailsResponse getOrderDetails(long orderId, long userId) {
        Order order = getOrder(orderId, userId);
        // todo get delivery info
        return OrderDetailsResponse.create(order);
    }

    @Override
    public void changeOrderDestination(OrderChangeDestinationRequest request, long orderId, long userId) {
        Order order = getOrder(orderId, userId);

        if (order.canNotChangeDestination())
            throw new UnexpectedOrderStatusException();

        order.setDestination(request.destination());
        orderRepository.save(order);
    }

    @Retryable(maxAttempts = 1, retryFor = PersistenceException.class)
    @Override
    public void cancelOrder(long orderId, long userId) {
        Order order = getOrder(orderId, userId);

        if (order.canNotCancel())
            throw new UnexpectedOrderStatusException();

        if (order.getStatus() == PICKUP_ASSIGNED) {
            deliveryClient.cancelDelivery(orderId);
        }
        order.setStatus(CANCELED);
        orderRepository.save(order);
    }

    @Override
    public List<OrderResponse> getAllOrders(long userId) {
        return orderRepository.findAllByUserId(userId).stream()
                .map(OrderResponse::create)
                .toList();
    }

    private Order getOrder(long orderId, long userId) {
        return orderRepository.findByIdAndUserId(orderId, userId)
                .orElseThrow(OrderNotFoundException::new);
    }
}
