package com.hepangda.ttms.model.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.hepangda.ttms.model.Employee;

public class LoginResponse {
    @JSONField(name = "ok")
    private boolean ok;

    @JSONField(name = "message")
    private String message;

    @JSONField(name = "user")
    private Employee user;

    private LoginResponse(boolean ok, String message) {
        this.ok = ok;
        this.message = message;
    }

    private LoginResponse(boolean ok, String message, Employee user) {
        this.ok = ok;
        this.message = message;
        this.user = user;
    }

    private LoginResponse(boolean ok, Employee user) {
        this.ok = ok;
        this.user = user;
    }

    private LoginResponse(boolean ok) {
        this.ok = ok;
    }

    static public LoginResponse createLoginLogout(boolean ok, String message) {
        return new LoginResponse(ok, message);
    }

    static public LoginResponse createCheckLogin(boolean ok, int uid, String name, int privilege) {
        Employee ee = new Employee();
        ee.setId(uid);
        ee.setName(name);
        ee.setPrivilege(privilege);
        return new LoginResponse(ok, ee);
    }

    static public LoginResponse createCheckLogin(boolean ok, Employee user) {
        return new LoginResponse(ok, user);
    }

    public boolean isOk() {
        return ok;
    }

    public void setOk(boolean ok) {
        this.ok = ok;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Employee getUser() {
        return user;
    }

    public void setUser(Employee user) {
        this.user = user;
    }
}
