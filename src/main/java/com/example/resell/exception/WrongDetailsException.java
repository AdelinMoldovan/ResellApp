package com.example.resell.exception;

public class WrongDetailsException extends RuntimeException {

    public WrongDetailsException() {
    }

    public WrongDetailsException(String message) {
        super(message);
    }
}