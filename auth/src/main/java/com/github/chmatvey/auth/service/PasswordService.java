package com.github.chmatvey.auth.service;

public interface PasswordService {
    String hashPassword(String password);

    boolean checkPassword(String password, String hash);
}
