package com.github.chmatvey.user.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record UserLogInRequest(
        @NotNull @Size(min = 4, max = 20) String login) {
}
