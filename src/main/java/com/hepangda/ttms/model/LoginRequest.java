package com.hepangda.ttms.model;

import com.alibaba.fastjson.annotation.JSONField;

public class LoginRequest {
    public LoginRequest(String loginName, String password) {
        this.loginName = loginName;
        this.password = password;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @JSONField(name="username")
    private String loginName;

    @JSONField(name="password")
    private String password;
}
