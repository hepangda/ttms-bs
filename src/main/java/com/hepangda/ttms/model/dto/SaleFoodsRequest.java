package com.hepangda.ttms.model.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.hepangda.ttms.model.SaleFoods;


public class SaleFoodsRequest {
    @JSONField(name = "type")
    private String type;
    @JSONField(name = "food")
    private SaleFoods saleFoods;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public SaleFoods getSaleFoods() {
        return saleFoods;
    }

    public void setSaleFoods(SaleFoods saleFoods) {
        this.saleFoods = saleFoods;
    }
}

