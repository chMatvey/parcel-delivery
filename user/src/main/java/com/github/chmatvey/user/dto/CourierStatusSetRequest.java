package com.github.chmatvey.user.dto;

import com.github.chmatvey.user.entity.CourierStatus;
import jakarta.validation.constraints.NotNull;

public record CourierStatusSetRequest(@NotNull CourierStatus status) {
}
