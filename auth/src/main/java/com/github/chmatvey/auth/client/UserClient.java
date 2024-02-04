package com.github.chmatvey.auth.client;

import com.github.chmatvey.auth.client.dto.UserCreateRequest;
import com.github.chmatvey.auth.client.dto.UserLogInRequest;
import com.github.chmatvey.auth.client.dto.UserLogInResponse;
import com.github.chmatvey.auth.dto.UserRegisterRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "UserClient", url = "${integration.user.url}")
public interface UserClient {
    @GetMapping("/log-in-info")
    UserLogInResponse logInInfo(UserLogInRequest request);

    @PostMapping
    void create(UserCreateRequest request);
}
