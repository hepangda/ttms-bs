package com.hepangda.ttms.model;

import com.alibaba.fastjson.annotation.JSONField;

public class LoginResponse {
    @JSONField(name="result")
    private boolean ok;

    private Employee logUser;

    public LoginResponse(boolean ok, Employee logUser) {
        this.ok = ok;
        this.logUser = logUser;
    }

    public void setOk(boolean ok) {
        this.ok = ok;
    }

    public void setLogUser(Employee logUser) {
        this.logUser = logUser;
    }

    public boolean isOk() {
        return ok;
    }

    public Employee getLogUser() {
        return logUser;
    }
}
