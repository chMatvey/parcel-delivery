package com.github.chmatvey.auth.client.dto;

import com.github.chmatvey.auth.dto.LogInRequest;

public record UserLogInRequest(String login) {
    public static UserLogInRequest create(LogInRequest request) {
        return new UserLogInRequest(request.login());
    }
}
