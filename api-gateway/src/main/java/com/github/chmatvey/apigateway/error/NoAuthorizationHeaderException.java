package com.github.chmatvey.apigateway.error;

import lombok.experimental.StandardException;

@StandardException
public class NoAuthorizationHeaderException extends IllegalArgumentException {
}
