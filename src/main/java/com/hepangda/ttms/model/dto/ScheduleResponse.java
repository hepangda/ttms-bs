package com.hepangda.ttms.model.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.hepangda.ttms.model.Schedule;
import com.hepangda.ttms.model.Studio;
import java.util.ArrayList;

public class ScheduleResponse {
    @JSONField(name = "ok")
    boolean ok;

    @JSONField(name ="message")
    String message;

    @JSONField(name = "studios")
    ArrayList<Schedule> studios;

    public ScheduleResponse(boolean ok, String message) {
        this.ok = ok;
        this.message = message;
    }

    public ScheduleResponse(boolean ok, String message, ArrayList<Schedule> studios) {
        this.ok = ok;
        this.message = message;
        this.studios = studios;
    }

    public boolean isOk() {
        return ok;
    }

    public void setOk(boolean ok) {
        this.ok = ok;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ArrayList<Schedule> getStudios() {
        return studios;
    }

    public void setStudios(ArrayList<Schedule> studios) {
        this.studios = studios;
    }
    public static ScheduleResponse createAddEditDelete(boolean ok,String message){
        return new ScheduleResponse(ok,message);
    }
    public static ScheduleResponse createFetch(boolean ok,String message,ArrayList<Schedule> studios){
        return new ScheduleResponse(ok,message,studios);
    }

}
