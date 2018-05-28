package com.hepangda.ttms.model;

import com.alibaba.fastjson.annotation.JSONField;

public class LoginResult {
    @JSONField(name="result")
    private boolean result;

    public LoginResult(boolean result) {
        this.result = result;
    }

    public boolean getResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }
}
