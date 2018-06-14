package com.hepangda.ttms.model;

import com.alibaba.fastjson.annotation.JSONField;
import com.hepangda.ttms.annotation.QueryKey;
import com.hepangda.ttms.annotation.QueryTable;

@QueryTable("Tickets")
public class Ticket {
    @JSONField(name = "id")
    @QueryKey(value = "TK_ID", insert = false, delete = true, primaryKey = true)
    private int id;

    @JSONField(name = "schid")
    @QueryKey(value = "Tk_SchID")
    private int schid;

    @JSONField(name = "seatid")
    @QueryKey(value = "TK_SeatID")
    private int seatid;

    @JSONField(name = "status")
    @QueryKey(value = "TK_Status")
    private int status;

    @JSONField(name = "row")
    private int row;

    @JSONField(name = "col")
    private int col;

    @JSONField(name = "price")
    private int price;

    public Ticket(int id) {
        this.id = id;
    }



    public Ticket(int schid, int seatid, int status) {
        this.schid = schid;
        this.seatid = seatid;
        this.status = status;
    }

    public Ticket() {
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSchid() {
        return schid;
    }

    public void setSchid(int schid) {
        this.schid = schid;
    }

    public int getSeatid() {
        return seatid;
    }

    public void setSeatid(int seatid) {
        this.seatid = seatid;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
