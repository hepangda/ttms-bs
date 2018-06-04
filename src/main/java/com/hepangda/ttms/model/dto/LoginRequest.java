package com.hepangda.ttms.model.dto;

import com.alibaba.fastjson.annotation.JSONField;

public class LoginRequest {
    public enum LoginRequestType {
        Login, CheckLogin, Logout
    }

    @JSONField(name = "type")
    private LoginRequestType type;

    @JSONField(name = "username")
    private String username;

    @JSONField(name = "password")
    private String password;

    public LoginRequestType getType() {
        return type;
    }

    public void setType(LoginRequestType type) {
        this.type = type;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
