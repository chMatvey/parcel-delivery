package com.github.chmatvey.jwt;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties("jwt")
public class JwtProperties {
    private String secret = "farel";
    private long expiration = 60;
}
