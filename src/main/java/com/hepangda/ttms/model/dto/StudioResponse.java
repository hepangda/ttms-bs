package com.hepangda.ttms.model.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.hepangda.ttms.model.Studio;

import java.util.ArrayList;

public class StudioResponse {
    @JSONField(name = "ok")
    private boolean ok;
    @JSONField(name = "message")
    private String message;
    @JSONField(name = "studios")
    private ArrayList<Studio> studios;

    public StudioResponse(boolean ok, String message) {
        this.ok = ok;
        this.message = message;
    }

    public StudioResponse(boolean ok, String message, ArrayList<Studio> studios) {
        this.ok = ok;
        this.message = message;
        this.studios = studios;
    }

    public static StudioResponse createAddEditDelete(boolean ok, String message) {
        return new StudioResponse(ok, message);
    }

    public static StudioResponse createFetch(boolean ok, String message, ArrayList<Studio> studios) {
        return new StudioResponse(ok, message, studios);
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

    public ArrayList<Studio> getStudios() {
        return studios;
    }

    public void setStudios(ArrayList<Studio> studios) {
        this.studios = studios;
    }
}
