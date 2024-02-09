package com.github.chmatvey.delivery.common.service;

import com.github.chmatvey.delivery.common.dto.OrderAssignMessage;
import com.github.chmatvey.delivery.common.entity.Delivery;
import com.github.chmatvey.delivery.common.repository.DeliveryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class RabbitListenerService {
    private final DeliveryRepository deliveryRepository;

    @RabbitListener(queues = "${message-broker.queues.order-assign}")
    public void listenOrderAssign(OrderAssignMessage message) {
        try {
            log.info("Order {} assigned to courier {}", message.orderId(), message.courierId());

            deliveryRepository.save(Delivery.builder()
                    .orderId(message.orderId())
                    .clientId(message.clientId())
                    .courierId(message.courierId())
                    .sourceAddress(message.origin())
                    .deliveryAddress(message.destination())
                    .build());
        } catch (Exception e) {
            log.error("Error while processing message = " + e.getMessage(), e);
        }
    }
}
