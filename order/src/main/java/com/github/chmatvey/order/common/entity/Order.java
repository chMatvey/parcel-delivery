package com.github.chmatvey.order.common.entity;

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

import static com.github.chmatvey.order.common.entity.OrderStatus.CREATED;
import static com.github.chmatvey.order.common.entity.OrderStatus.PICKUP_ASSIGNED;
import static jakarta.persistence.EnumType.STRING;
import static jakarta.persistence.GenerationType.SEQUENCE;
import static lombok.AccessLevel.PRIVATE;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@FieldDefaults(level = PRIVATE)
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @SequenceGenerator(name = "orders_id_seq", sequenceName = "orders_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = SEQUENCE, generator = "orders_id_seq")
    long id;

    @Column(name = "user_id", nullable = false)
    long userId;

    @Column(name = "courier_id")
    Long courierId;

    @Enumerated(STRING)
    @Column(nullable = false)
    @Builder.Default
    OrderStatus status = CREATED;

    @Column(nullable = false)
    String details;

    @Column(nullable = false)
    String origin;

    @Column(nullable = false)
    String destination;

    @Version
    Integer version;

    @UpdateTimestamp
    @Column(name = "updated_at")
    LocalDateTime updatedAt;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    LocalDateTime createdAt;

    public boolean canChangeDestination() {
        return status == CREATED;
    }

    public boolean canNotChangeDestination() {
        return !canChangeDestination();
    }

    public boolean canCancel() {
        return status == CREATED || status == PICKUP_ASSIGNED;
    }

    public boolean canNotCancel() {
        return !canCancel();
    }
}
