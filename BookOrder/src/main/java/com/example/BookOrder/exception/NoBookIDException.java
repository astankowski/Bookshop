package com.example.BookOrder.exception;

public class NoBookIDException extends RuntimeException {
    public NoBookIDException(String message) {
        super(message);
    }

    public NoBookIDException() {
    }
}
