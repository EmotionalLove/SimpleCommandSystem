package com.sasha.simplecmdsys.exception;

public class SimpleCommandException extends Error{
    public SimpleCommandException(String msg, String var) {
        super(msg);
        System.err.println(var);
    }
}
