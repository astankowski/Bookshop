package com.example.BookShop.exception;

public class InvalidBookIdException extends RuntimeException {
    public InvalidBookIdException() {
    }

    public InvalidBookIdException(String message) {
        super(message);
    }
}
