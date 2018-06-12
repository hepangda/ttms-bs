package com.hepangda.ttms.model.dto;


import com.alibaba.fastjson.annotation.JSONField;
import com.hepangda.ttms.model.Seat;

public class SeatRequest {
    @JSONField(name = "type")
    private String  type  ;

    @JSONField (name = "seat")
    private Seat seat ;


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Seat getSeat() {
        return seat;
    }

    public void setSeat(Seat seat) {
        this.seat = seat;
    }
}