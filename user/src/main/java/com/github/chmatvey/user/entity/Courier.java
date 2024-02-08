package com.github.chmatvey.user.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import static com.github.chmatvey.user.entity.CourierStatus.RESTING;
import static jakarta.persistence.EnumType.STRING;
import static lombok.AccessLevel.PRIVATE;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = PRIVATE)
@Entity
@Table(name = "couriers")
public class Courier {
    @Id
    @Column(name = "user_id")
    long userId;

    @Enumerated(STRING)
    @Column(nullable = false)
    @Builder.Default
    CourierStatus status = RESTING;
}
