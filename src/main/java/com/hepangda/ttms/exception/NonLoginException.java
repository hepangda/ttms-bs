package com.hepangda.ttms.exception;

public class NonLoginException extends Exception {
    @Override
    public String getMessage() {
        return "You\'re not loged in.";
    }
}
