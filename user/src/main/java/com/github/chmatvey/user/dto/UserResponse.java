package com.github.chmatvey.user.dto;

import com.github.chmatvey.user.entity.User;
import com.github.chmatvey.user.entity.UserRole;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record UserResponse(
        long id,
        String login,
        UserRole role,
        LocalDateTime createdAt
) {
    public static UserResponse create(User user) {
        return UserResponse.builder()
                .id(user.getId())
                .login(user.getLogin())
                .role(user.getRole())
                .createdAt(user.getCreatedAt())
                .build();
    }
}
