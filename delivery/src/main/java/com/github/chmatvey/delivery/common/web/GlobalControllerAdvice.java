package com.github.chmatvey.delivery.common.web;

import com.github.chmatvey.delivery.common.error.DeliveryNotFoundException;
import com.github.chmatvey.delivery.common.error.UnexpectedDeliveryStatusException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import static org.springframework.http.HttpStatus.CONFLICT;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@Slf4j
@ControllerAdvice
public class GlobalControllerAdvice extends ResponseEntityExceptionHandler {
    @ResponseStatus(NOT_FOUND)
    @ResponseBody
    public ErrorResponse handleDeliveryNotFoundException(DeliveryNotFoundException e) {
        log.error(e.getMessage(), e);
        return new ErrorResponse("Delivery not found");
    }

    @ResponseStatus(CONFLICT)
    @ResponseBody
    public ErrorResponse handleUnexpectedDeliveryStatusException(UnexpectedDeliveryStatusException e) {
        log.error(e.getMessage(), e);
        return new ErrorResponse("Unexpected delivery status");
    }
}
