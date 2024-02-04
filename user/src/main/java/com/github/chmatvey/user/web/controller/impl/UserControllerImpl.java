package com.github.chmatvey.user.web.controller.impl;

import com.github.chmatvey.user.dto.UserResponse;
import com.github.chmatvey.user.service.UserService;
import com.github.chmatvey.user.web.controller.UserController;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
@Slf4j
public class UserControllerImpl implements UserController {
    private final UserService userService;

    @GetMapping
    @Override
    public UserResponse getCurrentUser(@RequestHeader("User-Id") long userId) {
        log.info("Current user id = {}", userId);
        return userService.getUser(userId);
    }
}
