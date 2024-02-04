package com.github.chmatvey.apigateway.filter;

import com.github.chmatvey.apigateway.model.UserRole;
import com.github.chmatvey.jwt.model.UserInfo;
import com.github.chmatvey.jwt.service.JwtService;
import com.github.chmatvey.jwt.service.error.TokenVerificationException;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

import static org.springframework.http.HttpStatus.UNAUTHORIZED;

@Component
@Slf4j
public class JwtAuthGatewayFilterFactory extends AbstractGatewayFilterFactory<JwtAuthGatewayFilterFactory.Config> {
    private final JwtService jwtService;

    private static final String AUTHORIZATION_HEADER = "Authorization";
    private static final String USER_ID_HEADER = "User-Id";

    public JwtAuthGatewayFilterFactory(JwtService jwtService) {
        super(Config.class);
        this.jwtService = jwtService;
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            ServerHttpRequest request = exchange.getRequest();
            ServerHttpResponse response = exchange.getResponse();

            List<String> authorizationHeaders = request.getHeaders().get(AUTHORIZATION_HEADER);
            if (authorizationHeaders == null || authorizationHeaders.isEmpty()) {
                log.warn("No authorization header");
                response.setStatusCode(UNAUTHORIZED);
                return response.setComplete();
            }

            String token = authorizationHeaders.getFirst();
            try {
                UserInfo userInfo = jwtService.verifyToken(token);
                if (config.forbidden(userInfo.role())) {
                    log.warn("User with role {} is not allowed to access", userInfo.role());
                    response.setStatusCode(HttpStatus.FORBIDDEN);
                    return response.setComplete();
                }

                log.debug("User Info = {}", userInfo);
                request = request.mutate()
                        .header(USER_ID_HEADER, String.valueOf(userInfo.userId()))
                        .build();

            } catch (TokenVerificationException e) {
                log.warn("Error when verify token = " + e.getMessage());
                response.setStatusCode(UNAUTHORIZED);
                return response.setComplete();
            }

            return chain.filter(exchange.mutate()
                    .request(request)
                    .build());
        };
    }

    @Override
    public List<String> shortcutFieldOrder() {
        return List.of("roles");
    }

    @Data
    public static class Config {
        private Set<UserRole> roles;

        public boolean forbidden(String role) {
            return !roles.contains(UserRole.fromString(role));
        }
    }
}
