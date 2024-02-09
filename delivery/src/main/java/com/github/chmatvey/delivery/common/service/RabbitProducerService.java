package com.github.chmatvey.delivery.common.service;

import com.github.chmatvey.delivery.common.dto.DeliveryAcceptMessage;
import com.github.chmatvey.delivery.common.dto.DeliveryCompleteMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RabbitProducerService {
    private final RabbitTemplate rabbitTemplate;

    @Value("${message-broker.routing-keys.delivery-accept}")
    private String deliveryAcceptRoutingKey;

    @Value("${message-broker.routing-keys.delivery-complete}")
    private String deliveryCompleteRoutingKey;

    public void sendDeliveryAcceptedMessage(DeliveryAcceptMessage message) {
        rabbitTemplate.convertAndSend(deliveryAcceptRoutingKey, message);
    }

    public void sendDeliveryCompleteMessage(DeliveryCompleteMessage message) {
        rabbitTemplate.convertAndSend(deliveryCompleteRoutingKey, message);
    }
}
