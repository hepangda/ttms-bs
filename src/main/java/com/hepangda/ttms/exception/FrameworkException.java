package com.hepangda.ttms.exception;

public class FrameworkException extends RuntimeException {
    @Override
    public String getMessage() {
        return "Framework exception, check code please.";
    }
}
