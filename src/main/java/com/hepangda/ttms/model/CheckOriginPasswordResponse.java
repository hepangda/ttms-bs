package com.hepangda.ttms.model;

import com.alibaba.fastjson.annotation.JSONField;

public class CheckOriginPasswordResponse {
    @JSONField(name="ok")
    private boolean ok;

    public CheckOriginPasswordResponse(boolean ok) {
        this.ok = ok;
    }

    public boolean isOk() {
        return ok;
    }

    public void setOk(boolean ok) {
        this.ok = ok;
    }
}
