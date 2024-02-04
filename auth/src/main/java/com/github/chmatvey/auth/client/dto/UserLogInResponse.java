package com.github.chmatvey.auth.client.dto;

import com.github.chmatvey.jwt.model.UserInfo;
import lombok.Builder;

@Builder
public record UserLogInResponse(
        long userId,
        String password,
        String role
) {
    public UserInfo toUserInfo() {
        return new UserInfo(userId, role);
    }
}
