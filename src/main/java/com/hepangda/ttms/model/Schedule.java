package com.hepangda.ttms.model;

import com.alibaba.fastjson.annotation.JSONField;
import com.hepangda.ttms.annotation.QueryKey;
import com.hepangda.ttms.annotation.QueryTable;

@QueryTable("Schedule")
public class Schedule {
    @JSONField(name = "id")
    @QueryKey(value = "Sch_ID", delete = true, primaryKey = true, insert = false)
    private int ID;

    @JSONField(name = "time")
    @QueryKey("Sch_Time")
    private String time;

    @JSONField(name = "timeEnd")
    private String timeEnd;

    @JSONField(name = "stuid")
    @QueryKey("Sch_StuID")
    private int stuID;

    @JSONField(name = "movid")
    @QueryKey("Sch_MovID")
    private int movID;

    @JSONField(name = "price")
    @QueryKey("Sch_Price")
    private int price;

    public Schedule(int id) {

    }

    public Schedule() {
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getMovID() {
        return movID;
    }

    public void setMovID(int movID) {
        this.movID = movID;
    }

    public int getStuID() {
        return stuID;
    }

    public void setStuID(int stuID) {
        this.stuID = stuID;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
