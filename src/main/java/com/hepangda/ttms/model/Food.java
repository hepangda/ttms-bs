package com.hepangda.ttms.model;

import com.alibaba.fastjson.annotation.JSONField;
import com.hepangda.ttms.annotation.QueryKey;
import com.hepangda.ttms.annotation.QueryTable;

@QueryTable("foods")
public class Food {

    @JSONField(name = "id")
    @QueryKey(value = "Fod_ID", insert = false, delete = true, primaryKey = true)
    private int id;

    @JSONField(name = "name")
    @QueryKey(value = "Fod_name")
    private String name;

    @JSONField(name = "amount")
    @QueryKey(value = "Fod_Amount")
    private int amount;

    @JSONField(name = "price")
    @QueryKey(value = "Fod_Price")
    private int price;

    @JSONField(name = "imageurl")
    @QueryKey(value = "Fod_ImageURL")
    private String imageurl;

    public Food() {
    }

    public Food(int id, String name, int amount, int price, String imageurl) {
        this.id = id;
        this.name = name;
        this.amount = amount;
        this.price = price;
        this.imageurl = imageurl;
    }

    public Food(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }
}
