package com.example.resell.exception;

public class InvalidAdminException extends RuntimeException {

    public InvalidAdminException() {
    }

    public InvalidAdminException(String message) {
        super(message);
    }
}