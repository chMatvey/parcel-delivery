package com.github.chmatvey.user.dto;

import com.github.chmatvey.user.entity.UserRole;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record UserCreateRequest(
        @NotNull @Size(min = 4, max = 20) String login,
        @NotEmpty String password,
        @NotNull UserRole role) {
}
