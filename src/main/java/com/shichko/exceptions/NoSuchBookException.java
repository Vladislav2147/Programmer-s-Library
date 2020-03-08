package com.shichko.exceptions;

public class NoSuchBookException extends Exception {
    public NoSuchBookException(String message) {
        super(message);
    }
}
