package com.github.chmatvey.delivery.common.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

import static com.github.chmatvey.delivery.common.entity.DeliveryStatus.*;
import static jakarta.persistence.EnumType.STRING;
import static lombok.AccessLevel.PRIVATE;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@FieldDefaults(level = PRIVATE)
@Entity
@Table(name = "deliveries")
public class Delivery {
    @Id
    @Column(name = "order_id", nullable = false)
    long orderId;

    @Column(name = "client_id", nullable = false)
    long clientId;

    @Column(name = "courier_id", nullable = false)
    long courierId;

    @Enumerated(STRING)
    @Column(nullable = false)
    @Builder.Default
    DeliveryStatus status = CREATED;

    @Column(nullable = false)
    @Builder.Default
    boolean completed = false;

    @Column(nullable = false)
    @Builder.Default
    boolean canceled = false;

    @Column(name = "source_address", nullable = false)
    String sourceAddress;

    @Column(name = "delivery_address", nullable = false)
    String deliveryAddress;

    @Column(name = "delivery_date", nullable = false)
    LocalDateTime deliveryDate;

    @Version
    Integer version;

    @UpdateTimestamp
    @Column(name = "updated_at")
    LocalDateTime updatedAt;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    LocalDateTime createdAt;

    public Delivery accept() {
        return this.setStatus(ACCEPTED);
    }

    public Delivery complete() {
        return this.setCompleted(true)
                .setDeliveryDate(LocalDateTime.now())
                .setStatus(COMPLETED);
    }

    public Delivery cancel() {
        return this.setCanceled(true)
                .setStatus(CANCELLED);
    }
}
