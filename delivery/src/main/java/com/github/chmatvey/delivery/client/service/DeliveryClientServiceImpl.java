package com.github.chmatvey.delivery.client.service;

import com.github.chmatvey.delivery.client.dto.DeliveryDetails;
import com.github.chmatvey.delivery.common.error.DeliveryNotFoundException;
import com.github.chmatvey.delivery.common.repository.DeliveryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeliveryClientServiceImpl implements DeliveryClientService {
    private final DeliveryRepository deliveryRepository;

    @Override
    public DeliveryDetails getDeliveryDetails(long orderId, long clientId) {
        return deliveryRepository.findByOrderIdAndClientId(orderId, clientId)
                .map(DeliveryDetails::create)
                .orElseThrow(DeliveryNotFoundException::new);
    }
}
