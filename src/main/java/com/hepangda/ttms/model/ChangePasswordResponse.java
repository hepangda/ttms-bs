package com.hepangda.ttms.model;

import com.alibaba.fastjson.annotation.JSONField;

public class ChangePasswordResponse {
    @JSONField(name = "ok")
    private boolean ok;

    public ChangePasswordResponse(boolean ok) {
        this.ok = ok;
    }

    public boolean isOk() {
        return ok;
    }

    public void setOk(boolean ok) {
        this.ok = ok;
    }
}
