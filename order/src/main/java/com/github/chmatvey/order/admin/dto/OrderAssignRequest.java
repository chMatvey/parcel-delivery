package com.github.chmatvey.order.admin.dto;

import jakarta.validation.constraints.NotNull;

public record OrderAssignRequest(
        @NotNull long orderId,
        @NotNull long courierId
) {
}
