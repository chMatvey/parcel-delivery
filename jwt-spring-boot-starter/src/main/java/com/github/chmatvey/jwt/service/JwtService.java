package com.github.chmatvey.jwt.service;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.github.chmatvey.jwt.model.UserInfo;
import com.github.chmatvey.jwt.service.error.TokenVerificationException;

public interface JwtService {
    String generateToken(UserInfo response);

    UserInfo verifyToken(String token) throws TokenVerificationException;
}
