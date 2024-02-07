package com.github.chmatvey.user.service;

import com.github.chmatvey.user.dto.UserLogInRequest;
import com.github.chmatvey.user.dto.UserLogInResponse;
import com.github.chmatvey.user.entity.User;
import com.github.chmatvey.user.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;
import java.util.Optional;

import static com.github.chmatvey.user.entity.UserRole.CLIENT;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = UserService.class)
class UserServiceTest {
    @Autowired
    UserService userService;

    @MockBean
    UserRepository userRepository;

    @Test
    void logInInfo() {
        // Given
        UserLogInRequest request = UserLogInRequest.builder()
                .login("alex123")
                .build();
        User user = User.builder()
                .id(60224)
                .login("alex123")
                .role(CLIENT)
                .password("$argon2id$v=19$m=15360,t=2,p=1$ZmFyZWw$7RodV4cxBGiYbSSclRirBczZz7RUlifQhswExmrkYpw")
                .createdAt(LocalDateTime.now())
                .build();

        when(userRepository.findByLogin(request.login())).thenReturn(Optional.of(user));

        // When
        UserLogInResponse response = userService.logInInfo(request);

        // Then
        assertEquals(user.getId(), response.userId());
        assertEquals(user.getPassword(), response.password());
        assertEquals(user.getRole(), response.role());
    }
}