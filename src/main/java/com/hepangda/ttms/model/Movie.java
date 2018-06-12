package com.hepangda.ttms.model;

import com.alibaba.fastjson.annotation.JSONField;
import com.hepangda.ttms.annotation.QueryKey;
import com.hepangda.ttms.annotation.QueryTable;
import com.hepangda.ttms.annotation.ValidateKey;

@QueryTable("Movie")
public class Movie {

    @JSONField(name = "id")
    @QueryKey(value = "Mov_ID", delete = true, insert = false, primaryKey = true)
    private int id;

    @ValidateKey(maxLen = 80, errno = 308)
    @JSONField(name = "name")
    @QueryKey("Mov_Name")
    private String name;

    @ValidateKey(maxRange = 16, errno = 309)
    @JSONField(name = "type")
    @QueryKey("Mov_Type")
    private int type;

    @ValidateKey(maxRange = 4, errno = 310)
    @JSONField(name = "status")
    @QueryKey("Mov_Status")
    private int status;

    @ValidateKey(maxRange = 9, errno = 311)
    @JSONField(name = "religon")
    @QueryKey("Mov_Description")
    private int religon;

    @ValidateKey(maxLen = 150, errno = 312)
    @JSONField(name = "description")
    @QueryKey("Mov_Description")
    private String description;

    @ValidateKey(spf = 0, errno = 306)
    @JSONField(name = "time")
    @QueryKey(value = "Mov_Time", select = false)
    private String time;

    @JSONField(name = "image")
    @QueryKey(value = "Mov_ImageURL", select = false)
    private String image;

    public Movie(int id, String name, int type, int status, int religon, String description, String time, String image) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.status = status;
        this.religon = religon;
        this.description = description;
        this.time = time;
        this.image = image;
    }

    public Movie() {
    }

    public Movie(int id) {
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

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getReligon() {
        return religon;
    }

    public void setReligon(int religon) {
        this.religon = religon;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
