package com.github.chmatvey.order.client.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;

@Builder
public record OrderCreateRequest(
        @NotEmpty String details,
        @NotEmpty String origin,
        @NotEmpty String destination
) {
}
