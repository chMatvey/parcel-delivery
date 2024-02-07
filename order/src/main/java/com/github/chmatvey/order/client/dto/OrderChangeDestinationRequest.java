package com.github.chmatvey.order.client.dto;

import jakarta.validation.constraints.NotEmpty;

public record OrderChangeDestinationRequest(
        @NotEmpty String destination
) {
}
