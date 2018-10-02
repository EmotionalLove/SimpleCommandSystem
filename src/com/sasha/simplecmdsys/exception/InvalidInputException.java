package com.sasha.simplecmdsys.exception;

public class InvalidInputException extends SimpleCommandException {
    public InvalidInputException(String msg, String ex) {
        super(msg, ex);
    }
}
