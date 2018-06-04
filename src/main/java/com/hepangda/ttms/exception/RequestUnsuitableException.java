package com.hepangda.ttms.exception;

public class RequestUnsuitableException extends RuntimeException {
    @Override
    public String getMessage() {
        return "The request is unsuitable.";
    }
}
