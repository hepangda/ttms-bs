package com.hepangda.ttms.model;

import com.alibaba.fastjson.annotation.JSONField;
import com.hepangda.ttms.annotation.QueryKey;
import com.hepangda.ttms.annotation.QueryTable;


@QueryTable("Sales_Foods")
public class SaleFoods {
    @JSONField(name = "id")
    @QueryKey(value = "SF_ID", insert = false, delete = true, primaryKey = true)
    private int id;

    @JSONField(name = "fd_id")
    @QueryKey(value = "SF_FdID")
    private int fd_id;

    @JSONField(name = "price")
    @QueryKey(value = "SF_Price")
    private int price;

    public SaleFoods(int id, int fd_id, int price) {
        this.id = id;
        this.fd_id = fd_id;
        this.price = price;
    }

    public SaleFoods() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFd_id() {
        return fd_id;
    }

    public void setFd_id(int fd_id) {
        this.fd_id = fd_id;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
