package com.hepangda.ttms.model;


import com.alibaba.fastjson.annotation.JSONField;
import com.hepangda.ttms.annotation.QueryKey;
import com.hepangda.ttms.annotation.QueryTable;

/*
表名：Schedule
    Sch_ID SERIAL PRIMARY KEY,
    Sch_Time DATETIME NOT NULL,
    Sch_MovieID BIGINT UNSIGNED NOT NULL FK Movie(Mov_ID),
    Sch_StudioID BIGINT UNSIGNED NOT NULL FK Studio(Stu_ID),
    Sch_Price BIGINT NOT NULL
    UNIQUE KEY(Sch_Time, Sch_MovieID, Sch_StudioID)
 */
@QueryTable("Schedule")
public class Schedule {
//    @JSONField(name = "")
//    @QueryKey("Sch_ID")
//    private int ID;
//
//    @JSONField(name="")
//    @QueryKey("Sch_Time")
//    private String time;
//
//    @JSONField(name = "")
//    @QueryKey("Sch_MovieID")
//    private int movID;
//
//    @JSONField(name = "")
//    @QueryKey("Sche_StudioID")
//    private int stuID;
//
//    @JSONField(name = "")
//    @QueryKey("Sch_Price");
//    private int price;
//
//    public Schedule(int id) {
//
//    }
//
//    public int getID() {
//        return ID;
//    }
//
//    public void setID(int ID) {
//        this.ID = ID;
//    }
//
//    public String getTime() {
//        return time;
//    }
//
//    public void setTime(String time) {
//        this.time = time;
//    }
//
//    public int getMovID() {
//        return movID;
//    }
//
//    public void setMovID(int movID) {
//        this.movID = movID;
//    }
//
//    public int getStuID() {
//        return stuID;
//    }
//
//    public void setStuID(int stuID) {
//        this.stuID = stuID;
//    }
//
//    public int getPrice() {
//        return price;
//    }
//
//    public void setPrice(int price) {
//        this.price = price;
//    }
}
