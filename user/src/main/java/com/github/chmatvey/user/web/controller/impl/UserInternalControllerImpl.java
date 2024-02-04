package com.github.chmatvey.user.web.controller.impl;

import com.github.chmatvey.user.dto.UserCreateRequest;
import com.github.chmatvey.user.dto.UserLogInRequest;
import com.github.chmatvey.user.dto.UserLogInResponse;
import com.github.chmatvey.user.service.UserService;
import com.github.chmatvey.user.web.controller.UserInternalController;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RestController
@RequestMapping("/api/internal/v1/user")
@RequiredArgsConstructor
public class UserInternalControllerImpl implements UserInternalController {
    private final UserService userService;

    @PostMapping("/log-in-info")
    @Override
    public UserLogInResponse logInInfo(@RequestBody UserLogInRequest request) {
        return userService.logInInfo(request);
    }

    @PostMapping
    @Override
    public void createUser(@RequestBody UserCreateRequest request) {
        userService.createUser(request);
    }
}
