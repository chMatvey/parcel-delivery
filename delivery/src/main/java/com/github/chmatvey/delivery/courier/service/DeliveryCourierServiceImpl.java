package com.github.chmatvey.delivery.courier.service;

import com.github.chmatvey.delivery.common.client.OrderClient;
import com.github.chmatvey.delivery.common.entity.Delivery;
import com.github.chmatvey.delivery.common.error.DeliveryNotFoundException;
import com.github.chmatvey.delivery.common.error.UnexpectedDeliveryStatusException;
import com.github.chmatvey.delivery.common.repository.DeliveryRepository;
import com.github.chmatvey.delivery.courier.dto.DeliveryDetailsResponse;
import com.github.chmatvey.delivery.courier.dto.DeliveryDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.github.chmatvey.delivery.common.entity.DeliveryStatus.ACCEPTED;
import static com.github.chmatvey.delivery.common.entity.DeliveryStatus.CREATED;

@Service
@RequiredArgsConstructor
public class DeliveryCourierServiceImpl implements DeliveryCourierService {
    private final DeliveryRepository deliveryRepository;
    private final OrderClient orderClient;

    @Override
    public void acceptDelivery(long orderId, long courierId) {
        Delivery delivery = findDelivery(orderId, courierId);

        if (delivery.getStatus() != CREATED)
            throw new UnexpectedDeliveryStatusException(delivery.getStatus());

        deliveryRepository.save(delivery.accept());

        // todo replace om message pushing
        orderClient.acceptOrder(orderId);
    }

    @Override
    public void completeDelivery(long orderId, long courierId) {
        Delivery delivery = findDelivery(orderId, courierId);

        if (delivery.getStatus() != ACCEPTED)
            throw new UnexpectedDeliveryStatusException(delivery.getStatus());

        deliveryRepository.save(delivery.complete());
    }

    @Override
    public DeliveryDetailsResponse getDeliveryDetails(long orderId, long courierId) {
        Delivery delivery = findDelivery(orderId, courierId);

        return DeliveryDetailsResponse.create(delivery);
    }

    @Override
    public List<DeliveryDto> getCourierDeliveries(long courierId) {
        return deliveryRepository.findByCourierId(courierId);
    }

    private Delivery findDelivery(long orderId, long courierId) {
        return deliveryRepository.findByOrderIdAndClientId(orderId, courierId)
                .orElseThrow(DeliveryNotFoundException::new);
    }
}
