package com.hepangda.ttms.model.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.hepangda.ttms.model.Studio;
import com.hepangda.ttms.util.Utils;

import java.util.ArrayList;
public class StudioResponse {
    @JSONField(name = "ok")
    private boolean ok;
    @JSONField(name = "message")
    private String message;
    @JSONField(name = "studios")
    private ArrayList<Studio> studios;
    @JSONField(name = "page")
    private int page;
    @JSONField(name = "pageby")
    private int pageby;
    @JSONField(name = "total")
    private int total;

    public StudioResponse(boolean ok, String message) {
        this.ok = ok;
        this.message = message;
    }

    public StudioResponse(boolean ok, String message, ArrayList<Studio> studios) {
        this.ok = ok;
        this.message = message;
        this.studios = studios;
    }

    public static StudioResponse createFetch(boolean ok, String message, ArrayList<Studio> studios, StudioRequest req) {
        StudioResponse res = new StudioResponse(ok, message, Utils.slice(studios , req.getPage(), req.getPageby()));
        res.setTotal(studios.size());
        res.setPage(req.getPage());
        res.setPageby(req.getPageby());
        return res;
    }

    public static StudioResponse createAddEditDelete(boolean ok, String message) {
        return new StudioResponse(ok, message);
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

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPageby() {
        return pageby;
    }

    public void setPageby(int pageby) {
        this.pageby = pageby;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}