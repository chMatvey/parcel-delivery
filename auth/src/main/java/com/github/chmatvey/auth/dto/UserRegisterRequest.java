package com.github.chmatvey.auth.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;

@Builder
public record UserRegisterRequest(
        @NotNull @Size(min = 4, max = 20) String login,
        @NotNull @Size(min = 5, max = 20) String password
) {
}
