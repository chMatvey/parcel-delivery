package com.github.chmatvey.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.JWTVerifier;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import static com.github.chmatvey.jwt.JwtUtil.ROLE_CLAIM;
import static com.github.chmatvey.jwt.JwtUtil.USER_ID_CLAIM;

@AutoConfiguration
@ComponentScan(basePackages = "com.github.chmatvey.jwt")
@EnableConfigurationProperties(JwtProperties.class)
public class JwtAutoConfiguration {
    @Bean
    public Algorithm algorithm(JwtProperties properties) {
        return Algorithm.HMAC256(properties.getSecret());
    }

    @Bean
    public JWTVerifier verifier(Algorithm algorithm) {
        return JWT.require(algorithm)
                .withClaimPresence(USER_ID_CLAIM)
                .withClaimPresence(ROLE_CLAIM)
                .build();
    }
}
