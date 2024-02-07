package com.github.chmatvey.order.admin.service;

import com.github.chmatvey.order.admin.dto.OrderAssignRequest;
import com.github.chmatvey.order.admin.service.OrderAdminService;
import com.github.chmatvey.order.common.entity.Order;
import com.github.chmatvey.order.common.error.OrderNotFoundException;
import com.github.chmatvey.order.common.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.github.chmatvey.order.common.entity.OrderStatus.PICKUP_ASSIGNED;

@Service
@RequiredArgsConstructor
public class OrderAdminServiceImpl implements OrderAdminService {
    private final OrderRepository orderRepository;

    @Override
    public void assignOrderToCourier(OrderAssignRequest request) {
        // todo check courier id

        Order order = orderRepository.findById(request.courierId())
                .orElseThrow(OrderNotFoundException::new);

        orderRepository.save(order
                .setCourierId(request.courierId())
                .setStatus(PICKUP_ASSIGNED)
        );

        // todo send message to queue
    }
}
