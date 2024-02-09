package com.github.chmatvey.order.courier.service;

import com.github.chmatvey.order.common.entity.Order;
import com.github.chmatvey.order.common.error.OrderNotFoundException;
import com.github.chmatvey.order.common.error.UnexpectedOrderStatusException;
import com.github.chmatvey.order.common.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.github.chmatvey.order.common.entity.OrderStatus.PICKUP_ACCEPTED;
import static com.github.chmatvey.order.common.entity.OrderStatus.PICKUP_ASSIGNED;

@Service
@RequiredArgsConstructor
public class OrderCourierServiceImpl implements OrderCourierService {
    private final OrderRepository orderRepository;

    @Override
    public void acceptOrder(long orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(OrderNotFoundException::new);

        if (order.getStatus() != PICKUP_ASSIGNED)
            throw new UnexpectedOrderStatusException();

        orderRepository.save(order.setStatus(PICKUP_ACCEPTED));
    }
}
