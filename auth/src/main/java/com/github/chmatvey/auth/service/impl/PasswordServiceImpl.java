package com.github.chmatvey.auth.service.impl;

import com.github.chmatvey.auth.service.PasswordService;
import com.password4j.Hash;
import com.password4j.Password;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class PasswordServiceImpl implements PasswordService {
    @Value("${password.salt}")
    private String salt;

    @Override
    public String hashPassword(String password) {
        Hash hash = Password.hash(password)
                .addSalt(salt)
                .withArgon2();

        return hash.getResult();
    }

    @Override
    public boolean checkPassword(String password, String hash) {
        return Password.check(password, hash)
                .addSalt(salt)
                .withArgon2();
    }
}
