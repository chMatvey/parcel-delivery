package com.github.chmatvey.auth.service.impl;

import com.password4j.Password;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = PasswordServiceImpl.class)
@TestPropertySource(properties = {
        "password.salt=test"
})
class PasswordServiceImplTest {
    @Autowired
    PasswordServiceImpl passwordService;

    @Value("${password.salt}")
    String salt;

    @Test
    void hashPassword() {
        // Given
        String password = "r5CS.2kBYp!$7h>}";

        // When
        String hashedPassword = passwordService.hashPassword(password);

        // Then
        assertTrue(Password.check(password, hashedPassword)
                .addSalt(salt)
                .withArgon2());
    }

    @Test
    void checkPassword() {
        // Given
        String password = "GpLv2D)jC=ekzYf;";
        String hashedPassword = Password.hash(password)
                .addSalt(salt)
                .withArgon2()
                .getResult();

        // Then
        assertTrue(passwordService.checkPassword(password, hashedPassword));
    }
}