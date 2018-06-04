package com.hepangda.ttms.exception;

public class DangerSQLException extends RuntimeException {
    @Override
    public String getMessage() {
        return "There\'s a danger SQL command to execute";
    }
}
