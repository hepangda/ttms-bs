package com.hepangda.ttms.model.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.hepangda.ttms.model.SaleFoods;

import java.util.ArrayList;

public class SaleFoodsResponse {
    @JSONField(name = "ok")
    private boolean ok;
    @JSONField(name = "message")
    private String message;
    @JSONField(name = "foods")
    private ArrayList<SaleFoods> foods;

    public SaleFoodsResponse(boolean ok, String message) {
        this.ok = ok;
        this.message = message;
    }

    public SaleFoodsResponse(boolean ok, String message, ArrayList<SaleFoods> foods) {
        this.ok = ok;
        this.message = message;
        this.foods = foods;
    }

    public static SaleFoodsResponse createAddEditDelete(boolean ok, String message) {
        return new SaleFoodsResponse(ok, message);
    }

    public static SaleFoodsResponse createFetch(boolean ok, String message, ArrayList<SaleFoods> foods) {
        return new SaleFoodsResponse(ok, message, foods);
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

    public ArrayList<SaleFoods> getFoods() {
        return foods;
    }

    public void setFoods(ArrayList<SaleFoods> foods) {
        this.foods = foods;
    }
}
