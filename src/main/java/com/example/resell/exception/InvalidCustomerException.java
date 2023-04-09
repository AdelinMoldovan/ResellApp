package com.example.resell.exception;

public class InvalidCustomerException extends RuntimeException {

    public InvalidCustomerException() {
    }

    public InvalidCustomerException(String message) {
        super(message);
    }
}