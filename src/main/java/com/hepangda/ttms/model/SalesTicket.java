package com.hepangda.ttms.model;

import com.alibaba.fastjson.annotation.JSONField;
import com.hepangda.ttms.annotation.QueryKey;
import com.hepangda.ttms.annotation.QueryTable;

/*
表名：SalesTicket
    ST_ID SERIAL PRIMARY KEY,
    ST_TkID BIGINT UNSIGNED NOT NULL FK Tickets(Tk_ID),
    ST_Price BIGINT NOT NULL  >=0*/
@QueryTable("SalesTicket")
public class SalesTicket {
    @JSONField(name = "uid")
    @QueryKey(value = "ST_ID",insert = false, delete = true, primaryKey = true)
    private int id;

    @JSONField(name = "utkid")
    @QueryKey(value = "ST_TkID")
    private int tkid;

    @JSONField(name = "price")
    @QueryKey(value = "ST_Price")
    private int price;

    public SalesTicket() {
    }

    public SalesTicket(int id) {
        this.id = id;
    }

    public SalesTicket(int tkid, int price) {
        this.tkid = tkid;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTkid() {
        return tkid;
    }

    public void setTkid(int tkid) {
        this.tkid = tkid;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}