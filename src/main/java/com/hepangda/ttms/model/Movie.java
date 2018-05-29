package com.hepangda.ttms.model;

import java.time.Duration;
import java.util.Date;

public class Movie {
    private int id;
    private String name;
    private int religon;
    private int type;
    private String description;
    private Duration time;

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setReligon(int religon) {
        this.religon = religon;
    }

    public void setType(int type) {
        this.type = type;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setTime(Duration time) {
        this.time = time;
    }
}
