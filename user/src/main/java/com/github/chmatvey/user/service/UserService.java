package com.github.chmatvey.user.service;

import com.github.chmatvey.user.dto.*;
import com.github.chmatvey.user.entity.User;
import com.github.chmatvey.user.repository.UserRepository;
import com.github.chmatvey.user.service.error.LoginAlreadyExisted;
import com.github.chmatvey.user.service.error.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public UserLogInResponse logInInfo(UserLogInRequest request) {
        return userRepository.findByLogin(request.login())
                .map(UserLogInResponse::create)
                .orElseThrow(UserNotFoundException::new);
    }

    public void createUser(UserCreateRequest request) {
        if (userRepository.existsByLogin(request.login()))
            throw new LoginAlreadyExisted();

        userRepository.save(User.builder()
                .login(request.login())
                .password(request.password())
                .role(request.role())
                .build());
    }

    public UserResponse getUser(long userId) {
        return userRepository.findById(userId)
                .map(UserResponse::create)
                .orElseThrow(UserNotFoundException::new);
    }
}
