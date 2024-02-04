package com.github.chmatvey.jwt.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import com.github.chmatvey.jwt.JwtProperties;
import com.github.chmatvey.jwt.model.UserInfo;
import com.github.chmatvey.jwt.service.error.TokenVerificationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static com.github.chmatvey.jwt.JwtUtil.ROLE_CLAIM;
import static com.github.chmatvey.jwt.JwtUtil.USER_ID_CLAIM;
import static org.junit.jupiter.api.Assertions.*;

class JwtServiceImplTest {
    JwtServiceImpl jwtService;
    JwtProperties properties;
    Algorithm algorithm;
    JWTVerifier verifier;

    @BeforeEach
    void setUp() {
        properties = new JwtProperties();
        algorithm = Algorithm.HMAC256(properties.getSecret());
        verifier = JWT.require(algorithm)
                .withClaimPresence("userId")
                .withClaimPresence("role")
                .build();
        jwtService = new JwtServiceImpl(algorithm, verifier, properties);
    }

    @Test
    void generateToken() {
        // Given
        UserInfo userInfo = UserInfo.builder()
                .userId(30224)
                .role("ADMIN")
                .build();

        // When
        String token = jwtService.generateToken(userInfo);

        // Then
        assertNotNull(token);
        DecodedJWT decodedJWT = verifier.verify(token);
        assertEquals(userInfo.userId(), decodedJWT.getClaim("userId").asLong());
        assertEquals(userInfo.role(), decodedJWT.getClaim("role").asString());
    }

    @Test
    void verifyToken() {
        // Given
        String token = JWT.create()
                .withClaim(USER_ID_CLAIM, 3022400)
                .withClaim(ROLE_CLAIM, "CLIENT")
                .withExpiresAt(new Date(new Date().getTime() + 100_000))
                .sign(algorithm);

        // When
        UserInfo userInfo = jwtService.verifyToken(token);

        // Then
        assertEquals(3022400, userInfo.userId());
        assertEquals("CLIENT", userInfo.role());
    }

    @Test
    void verifyTokenWhenItNotValid() {
        // Given
        String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VySWQiOjMwMjI0LCJyb2xlIjoiQURNSU4iLCJleHAiOjE3MDY5fQ.Oo3n2Kk0X4i8AvoGfBVilL5YrV9nstXHB4cfL0Gq6JA";

        // Then
        assertThrows(TokenVerificationException.class, () -> jwtService.verifyToken(token));
    }

    @Test
    void verifyTokenWhenItExpired() {
        // Given
        String token = JWT.create()
                .withClaim(USER_ID_CLAIM, 3022400)
                .withClaim(ROLE_CLAIM, "CLIENT")
                .withExpiresAt(new Date(new Date().getTime() - 100_000))
                .sign(algorithm);

        // Then
        assertThrows(TokenVerificationException.class, () -> jwtService.verifyToken(token));
    }
}