package com.shichko.exceptions;

public class ReaderAlreadyHasBookException extends Exception {
    public ReaderAlreadyHasBookException(String message) {
        super(message);
    }
}
