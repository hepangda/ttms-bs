package com.hepangda.ttms.model;

public class CheckOriginPasswordRequest {
    private String username;
    private String tryPassword;

    public CheckOriginPasswordRequest(String username, String tryPassword) {
        this.username = username;
        this.tryPassword = tryPassword;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getTryPassword() {
        return tryPassword;
    }

    public void setTryPassword(String tryPassword) {
        this.tryPassword = tryPassword;
    }
}
