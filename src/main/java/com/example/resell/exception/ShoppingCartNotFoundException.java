package com.example.resell.exception;

public class ShoppingCartNotFoundException extends  RuntimeException{

    public ShoppingCartNotFoundException() {
    }

    public ShoppingCartNotFoundException(String message) {
        super(message);
    }
}
