package com.github.chmatvey.delivery.common.repository;

import com.github.chmatvey.delivery.common.entity.Delivery;
import com.github.chmatvey.delivery.courier.dto.DeliveryDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface DeliveryRepository extends JpaRepository<Delivery, Long> {
    Optional<Delivery> findByOrderIdAndClientId(long orderId, long clientId);

    Optional<Delivery> findByOrderIdAndCourierId(long orderId, long courierId);

    @Query("select new com.github.chmatvey.delivery.courier.dto.DeliveryDto(d.orderId, d.status, d.updatedAt) from Delivery d " +
            "where d.courierId =:courierId and d.completed = false and d.canceled = false")
    List<DeliveryDto> findByCourierId(long courierId);
}
