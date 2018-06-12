package com.hepangda.ttms.model.dto;

import com.alibaba.fastjson.annotation.JSONField;

public class SeatResponse {
    @JSONField (name = "ok")
    private boolean ok;

    @JSONField(name = "message")
    private String message;

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

    public SeatResponse(boolean ok, String message) {
        this.ok = ok;
        this.message = message;
    }
    public   static SeatResponse createUpdate(boolean ok ,String message){
        return  new SeatResponse(ok,message);

    }
}