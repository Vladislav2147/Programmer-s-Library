package com.ShichkoVlad.Exceptions;

import java.util.NoSuchElementException;

public class NoSuchBookException extends NoSuchElementException {
    public NoSuchBookException(String message) {
        super(message);
    }
}
