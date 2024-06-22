package com.example.BookShop.exception;


import com.example.BookOrder.exception.OrderExceptionHandler;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.IOException;
import java.util.stream.Collectors;

@ControllerAdvice
public class BookExceptionHandler {
    Logger logger = LogManager.getLogger(OrderExceptionHandler.class);

    @ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Author not found")
    @ExceptionHandler(InvalidAuthorIdException.class)
    public void orderNotFound(HttpServletRequest req, Exception ex) {
        logger.warn("Author not found at {} ", req.getRequestURL());
    }

    @ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Book not found")
    @ExceptionHandler(InvalidBookIdException.class)
    public void bookNotFound(HttpServletRequest req, Exception ex) {
        logger.warn("Book not found at {}", req.getRequestURL());
    }

    @ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Bad Book fields configuration")
    @ExceptionHandler(InvalidBookCreateRequestException.class)
    public void invalidBookCreateRequestException(HttpServletRequest req, Exception ex) {
        try {
            logger.warn("bad Book request {}",
                    req.getReader().lines().collect(Collectors.joining(System.lineSeparator()))
            );
        } catch (IOException e) {
            logger.warn("bad Book request no request body");
        }
    }
}
