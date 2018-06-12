package com.hepangda.ttms.exception;

public class DangerSQLException extends RuntimeException {
    public DangerSQLException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return "There\'s a danger SQL command to execute: " + getMessage();
    }
}
