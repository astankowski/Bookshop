package com.example.BookOrder.exception;

public class InvalidBookIdException extends RuntimeException {
    public InvalidBookIdException() {
    }

    public InvalidBookIdException(String message) {
        super(message);
    }
}
