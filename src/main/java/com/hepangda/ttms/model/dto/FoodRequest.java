package com.hepangda.ttms.model.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.hepangda.ttms.model.Food;
import com.hepangda.ttms.model.Studio;

public class FoodRequest {
    @JSONField(name = "type")
    private String type;
    @JSONField(name = "food")
    private Food food;
    @JSONField(name = "page")
    private String page;
    @JSONField(name = "pageby")
    private String pageby;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Food getFood() {
        return food;
    }

    public void setFood(Food food) {
        this.food = food;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public String getPageby() {
        return pageby;
    }

    public void setPageby(String pageby) {
        this.pageby = pageby;
    }
}
