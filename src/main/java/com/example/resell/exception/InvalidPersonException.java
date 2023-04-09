package com.example.resell.exception;

public class InvalidPersonException extends RuntimeException {

    public InvalidPersonException() {
    }

    public InvalidPersonException(String message) {
        super(message);
    }
}