package com.github.chmatvey.jwt.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import com.github.chmatvey.jwt.JwtProperties;
import com.github.chmatvey.jwt.model.UserInfo;
import com.github.chmatvey.jwt.service.error.TokenVerificationException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;

import static com.github.chmatvey.jwt.JwtUtil.ROLE_CLAIM;
import static com.github.chmatvey.jwt.JwtUtil.USER_ID_CLAIM;

@Service
@RequiredArgsConstructor
@Slf4j
public class JwtServiceImpl implements JwtService {
    private final Algorithm algorithm;
    private final JWTVerifier verifier;
    private final JwtProperties jwtProperties;

    @Override
    public String generateToken(UserInfo userInfo) {
        long expirationMillis = jwtProperties.getExpiration() * 1000;
        Date now = new Date();
        Date exp = new Date(now.getTime() + expirationMillis);

        return JWT.create()
                .withClaim(USER_ID_CLAIM, userInfo.userId())
                .withClaim(ROLE_CLAIM, userInfo.role())
                .withExpiresAt(exp)
                .sign(algorithm);
    }

    @Override
    public UserInfo verifyToken(String token) throws TokenVerificationException {
        try {
            DecodedJWT decodedJWT = verifier.verify(token);
            return UserInfo.builder()
                    .userId(decodedJWT.getClaim(USER_ID_CLAIM).asLong())
                    .role(decodedJWT.getClaim(ROLE_CLAIM).asString())
                    .build();
        } catch (JWTVerificationException e) {
            log.warn("Error when verify JWT toke = " + e.getMessage());
            throw new TokenVerificationException(e.getMessage(), e);
        }
    }
}
