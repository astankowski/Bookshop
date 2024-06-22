package com.example.BookOrder.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class OrderExceptionHandler {
    Logger logger = LogManager.getLogger(OrderExceptionHandler.class);

    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, reason = "Order not found")
    @ExceptionHandler(NoBookIDException.class)
    public void orderNotFound(HttpServletRequest req, Exception ex) {
        logger.error("Order not found", ex.getCause().toString());
    }
}
