package com.hepangda.ttms.model;


import com.alibaba.fastjson.annotation.JSONField;
import com.hepangda.ttms.annotation.QueryKey;
/*
 id: (int)演出计划id,
      time: (string)演出计划时间 格式yyyy-mm-dd hh:mm:ss,
      stuname: (string)演出厅名字,
      movname: (string)影片名字,
      movimage: (string)影片图片url,
      price: (int)场次价格
 */
@QueryKey("view_SchFetch")
public class ScheduleFetch {
    @JSONField(name ="id")
    @QueryKey("Stu_ID")
    private int id;

    @JSONField(name = "time")
    @QueryKey("Sch_Time")
    private String time;

    @JSONField(name = "stuname")
    @QueryKey("Stu_name")
    private String stuname;

    @JSONField(name = "movname")
    @QueryKey("Mov_Name")
    private String movname;

    @JSONField(name = "movimage")
    @QueryKey("Mov_Imageurl")
    private String movimage;

    @JSONField(name = "price")
    @QueryKey("Sch_Price")
    private int price;

    public ScheduleFetch() {
    }

    public ScheduleFetch(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getStuname() {
        return stuname;
    }

    public void setStuname(String stuname) {
        this.stuname = stuname;
    }

    public String getMovname() {
        return movname;
    }

    public void setMovname(String movname) {
        this.movname = movname;
    }

    public String getMovimage() {
        return movimage;
    }

    public void setMovimage(String movimage) {
        this.movimage = movimage;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
