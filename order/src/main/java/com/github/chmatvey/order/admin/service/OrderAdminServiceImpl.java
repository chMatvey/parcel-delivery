package com.github.chmatvey.order.admin.service;

import com.github.chmatvey.order.admin.dto.OrderAssignMessage;
import com.github.chmatvey.order.admin.dto.OrderAssignRequest;
import com.github.chmatvey.order.common.entity.Order;
import com.github.chmatvey.order.common.error.OrderNotFoundException;
import com.github.chmatvey.order.common.error.UnexpectedOrderStatusException;
import com.github.chmatvey.order.common.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import static com.github.chmatvey.order.common.entity.OrderStatus.CREATED;
import static com.github.chmatvey.order.common.entity.OrderStatus.PICKUP_ASSIGNED;

@Service
@RequiredArgsConstructor
public class OrderAdminServiceImpl implements OrderAdminService {
    private final OrderRepository orderRepository;
    private final RabbitTemplate rabbitTemplate;

    @Override
    public void assignOrderToCourier(OrderAssignRequest request) {
        // todo check courier id

        Order order = orderRepository.findById(request.orderId())
                .orElseThrow(OrderNotFoundException::new);

        if (order.getStatus() != CREATED)
            throw new UnexpectedOrderStatusException();

        orderRepository.save(order
                .setCourierId(request.courierId())
                .setStatus(PICKUP_ASSIGNED)
        );

        rabbitTemplate.convertAndSend(OrderAssignMessage.create(order));
    }
}
