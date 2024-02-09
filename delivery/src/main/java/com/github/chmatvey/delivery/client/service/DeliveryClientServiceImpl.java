package com.github.chmatvey.delivery.client.service;

import com.github.chmatvey.delivery.client.dto.DeliveryDetails;
import com.github.chmatvey.delivery.common.entity.Delivery;
import com.github.chmatvey.delivery.common.error.DeliveryNotFoundException;
import com.github.chmatvey.delivery.common.error.UnexpectedDeliveryStatusException;
import com.github.chmatvey.delivery.common.repository.DeliveryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.github.chmatvey.delivery.common.entity.DeliveryStatus.CREATED;

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

    @Override
    public void cancelDelivery(long orderId) {
        Delivery delivery = deliveryRepository.findById(orderId)
                .orElseThrow(DeliveryNotFoundException::new);

        if (delivery.getStatus() != CREATED)
            throw new UnexpectedDeliveryStatusException(delivery.getStatus());

        deliveryRepository.save(delivery.cancel());
    }
}
