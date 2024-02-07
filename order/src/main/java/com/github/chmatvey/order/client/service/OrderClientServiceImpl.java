package com.github.chmatvey.order.client.service;

import com.github.chmatvey.order.client.dto.*;
import com.github.chmatvey.order.client.service.OrderClientService;
import com.github.chmatvey.order.common.entity.Order;
import com.github.chmatvey.order.common.error.OrderNotFoundException;
import com.github.chmatvey.order.common.error.UnexpectedOrderStatusException;
import com.github.chmatvey.order.common.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.github.chmatvey.order.common.entity.OrderStatus.CANCELED;

@Service
@RequiredArgsConstructor
public class OrderClientServiceImpl implements OrderClientService {
    private final OrderRepository orderRepository;

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

    @Override
    public void cancelOrder(long orderId, long userId) {
        Order order = getOrder(orderId, userId);

        if (order.canNotCancel())
            throw new UnexpectedOrderStatusException();

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
