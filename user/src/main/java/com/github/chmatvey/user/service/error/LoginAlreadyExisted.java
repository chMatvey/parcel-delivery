package com.github.chmatvey.user.service.error;

import lombok.experimental.StandardException;

@StandardException
public class LoginAlreadyExisted extends RuntimeException {
}
