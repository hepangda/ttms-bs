package com.hepangda.ttms.model.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.hepangda.ttms.model.Food;
import com.hepangda.ttms.model.Studio;

import java.util.ArrayList;

public class FoodResponse {
    @JSONField(name = "ok")
    private boolean ok;
    @JSONField (name = "message")
    private String  message;
    @JSONField (name = "foods")
    private ArrayList<Food> foods;

    public FoodResponse(boolean ok , String message) {
        this.ok = ok;
        this.message = message;
    }

    public FoodResponse(boolean ok , String message , ArrayList<Food> foods){
        this.ok = ok;
        this.message = message;
        this.foods = foods;
    }

    public static com.hepangda.ttms.model.dto.FoodResponse createAddEditDelete(boolean ok , String message) {
        return new com.hepangda.ttms.model.dto.FoodResponse(ok , message);
    }

    public static com.hepangda.ttms.model.dto.FoodResponse createFetch(boolean ok , String message , ArrayList<Food> foods) {
        return new com.hepangda.ttms.model.dto.FoodResponse(ok , message , foods);
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

    public ArrayList<Food> getFoods() {
        return foods;
    }

    public void setFoods(ArrayList<Food> foods) {
        this.foods = foods;
    }
}
