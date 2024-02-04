package com.github.chmatvey.apigateway.model;

public enum UserRole {
    CLIENT,
    COURIER,
    ADMIN;

    public static UserRole fromString(String role) {
        return UserRole.valueOf(role);
    }
}
