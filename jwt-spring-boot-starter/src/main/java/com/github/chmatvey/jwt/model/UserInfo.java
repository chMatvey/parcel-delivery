package com.github.chmatvey.jwt.model;

import lombok.Builder;

@Builder
public record UserInfo(
        long userId,
        String role
) {
}
