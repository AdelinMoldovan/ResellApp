package com.example.resell.exception;

public class InvalidProductException extends RuntimeException {

    public InvalidProductException() {
    }

    public InvalidProductException(String message) {
        super(message);
    }
}