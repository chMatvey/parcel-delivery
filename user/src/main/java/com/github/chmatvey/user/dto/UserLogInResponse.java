package com.github.chmatvey.user.dto;

import com.github.chmatvey.user.entity.User;
import com.github.chmatvey.user.entity.UserRole;

public record UserLogInResponse(
        long userId,
        String password,
        UserRole role) {
    public static UserLogInResponse create(User user) {
        return new UserLogInResponse(user.getId(), user.getPassword(), user.getRole());
    }
}
