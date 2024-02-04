package com.github.chmatvey.user.web;

import com.github.chmatvey.user.dto.ErrorResponse;
import com.github.chmatvey.user.service.error.LoginAlreadyExisted;
import com.github.chmatvey.user.service.error.UserNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import static org.springframework.http.HttpStatus.*;

@ControllerAdvice
@Slf4j
public class GlobalControllerAdvice extends ResponseEntityExceptionHandler {
    @ResponseStatus(BAD_REQUEST)
    @ResponseBody
    public ErrorResponse handleLoginAlreadyExisted(LoginAlreadyExisted e) {
        log.warn(e.getMessage(), e);
        return new ErrorResponse("Login already exist");
    }

    @ResponseStatus(NOT_FOUND)
    @ResponseBody
    public ErrorResponse handleUserNotFoundException(UserNotFoundException e) {
        log.warn(e.getMessage(), e);
        return new ErrorResponse("User not found");
    }

    @ResponseStatus(INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ErrorResponse handleException(Exception e) {
        log.error(e.getMessage(), e);
        return new ErrorResponse("Server error");
    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception e, Object body, HttpHeaders headers, HttpStatusCode statusCode, WebRequest request) {
        log.warn(e.getMessage(), e);
        body = new ErrorResponse(e.getMessage());
        return new ResponseEntity<>(body, headers, statusCode);
    }
}
