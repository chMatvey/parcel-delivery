package com.github.chmatvey.user.dto;

import com.github.chmatvey.user.entity.Courier;
import com.github.chmatvey.user.entity.CourierStatus;
import lombok.Builder;

@Builder
public record CourierResponse(long userId, CourierStatus status) {
    public static CourierResponse create(Courier courier) {
        return CourierResponse.builder()
                .userId(courier.getUserId())
                .status(courier.getStatus())
                .build();
    }
}
