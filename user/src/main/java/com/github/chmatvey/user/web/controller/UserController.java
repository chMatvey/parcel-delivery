package com.github.chmatvey.user.web.controller;

import com.github.chmatvey.user.dto.UserResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "User API")
public interface UserController {
    @Operation(description = "Get current user info")
    UserResponse getCurrentUser(long userId);
}
