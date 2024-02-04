package com.github.chmatvey.auth.client.dto;

import lombok.Builder;

@Builder
public record UserCreateRequest(
        String login,
        String password,
        UserRole role
) {
}
