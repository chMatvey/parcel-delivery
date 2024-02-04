package com.github.chmatvey.auth.web.controller;

import com.github.chmatvey.auth.dto.LogInRequest;
import com.github.chmatvey.auth.dto.LogInResponse;
import com.github.chmatvey.auth.dto.UserRegisterRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Authentication API")
public interface AuthController {
    @Operation(description = "Register client account")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Successfully registered"),
            @ApiResponse(responseCode = "400")
    })
    void register(UserRegisterRequest request);

    @Operation(description = "Log in")
    @ApiResponses({
            @ApiResponse(responseCode = "200"),
            @ApiResponse(responseCode = "400")
    })
    LogInResponse logIn(LogInRequest request);
}
