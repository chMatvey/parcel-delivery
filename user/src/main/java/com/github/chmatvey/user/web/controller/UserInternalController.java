package com.github.chmatvey.user.web.controller;

import com.github.chmatvey.user.dto.UserCreateRequest;
import com.github.chmatvey.user.dto.UserLogInRequest;
import com.github.chmatvey.user.dto.UserLogInResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@Tag(name = "User internal API")
public interface UserInternalController {
    @Operation(description = "Find user by login and password")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "User successfully found"),
            @ApiResponse(responseCode = "404", description = "Not found by login")
    })
    UserLogInResponse logInInfo(@Valid UserLogInRequest request);

    @Operation(description = "Create user")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "User successfully created"),
            @ApiResponse(responseCode = "400", description = "Login already existed")
    })
    void createUser(@Valid UserCreateRequest request);
}
