package com.github.chmatvey.user.web.controller;

import com.github.chmatvey.user.dto.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@Tag(name = "User API")
public interface UserController {
    @Operation(description = "Get current user info")
    UserResponse getCurrentUser(long userId);
}
