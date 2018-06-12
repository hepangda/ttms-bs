package com.hepangda.ttms.model;

import com.alibaba.fastjson.annotation.JSONField;
import com.hepangda.ttms.annotation.QueryKey;
import com.hepangda.ttms.annotation.QueryTable;

@QueryTable("tickets")
public class Ticket {
    @JSONField(name = "uid")
    @QueryKey(value = "TK_ID", insert = false, delete = true, primaryKey = true)
    private int id;

    @JSONField(name = "uschid")
    @QueryKey(value = "Tk_SchID")
    private int schid;

    @JSONField(name = "useatid")
    @QueryKey(value = "TK_SeatID")
    private int seatid;

    @JSONField(name = "status")
    @QueryKey(value = "TK_Status")
    private int status;

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
