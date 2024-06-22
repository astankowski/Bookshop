package com.example.BookOrder.exception;

public class InvalidAuthorIdException extends RuntimeException {
    public InvalidAuthorIdException() {
    }

    public InvalidAuthorIdException(String message) {
        super(message);
    }
}
