package com.github.chmatvey.order.common.service;

import com.github.chmatvey.order.common.dto.DeliveryAcceptMessage;
import com.github.chmatvey.order.common.dto.DeliveryCompleteMessage;
import com.github.chmatvey.order.common.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import static com.github.chmatvey.order.common.entity.OrderStatus.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class RabbitListenerService {
    private final OrderRepository orderRepository;

    @RabbitListener(queues = "${message-broker.queues.delivery-accept}")
    public void listenDeliveryAccept(DeliveryAcceptMessage message) {
        try {
            orderRepository.findById(message.orderId()).ifPresentOrElse(
                    (order) -> {
                        if (order.getStatus() != PICKUP_ASSIGNED)
                            log.error("Order {} status is {}", message.orderId(), order.getStatus());

                        log.info("Order {} status updated to PICKUP_ACCEPTED", message.orderId());
                        orderRepository.save(order.setStatus(PICKUP_ACCEPTED));
                    },
                    () -> log.error("Order {} not found", message.orderId())
            );
        } catch (Exception e) {
            log.error("Error while processing message = " + e.getMessage(), e);
        }
    }

    @RabbitListener(queues = "${message-broker.queues.delivery-complete}")
    public void listenDeliveryComplete(DeliveryCompleteMessage message) {
        try {
            orderRepository.findById(message.orderId()).ifPresentOrElse(
                    (order) -> {
                        if (order.getStatus() != PICKUP_ACCEPTED)
                            log.error("Order {} status is {}", message.orderId(), order.getStatus());

                        log.info("Order {} status updated to DELIVERED", message.orderId());
                        orderRepository.save(order.setStatus(DELIVERED));
                    },
                    () -> log.error("Order {} not found", message.orderId())
            );
        } catch (Exception e) {
            log.error("Error while processing message = " + e.getMessage(), e);
        }
    }
}
