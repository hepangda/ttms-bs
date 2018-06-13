package com.hepangda.ttms.model.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.hepangda.ttms.model.Schedule;
import com.hepangda.ttms.model.Studio;
import com.hepangda.ttms.util.Utils;

import java.util.ArrayList;

public class ScheduleResponse {
    @JSONField(name = "ok")
    private boolean ok;

    @JSONField(name = "message")
    private String message;

    @JSONField(name = "page")
    private int page;

    @JSONField(name = "pageby")
    private int pageby;

    @JSONField(name  = "total")
    private int total;


    @JSONField(name = "schedule")
    ArrayList<Schedule> schedule;

    public ScheduleResponse(boolean ok, String message) {
        this.ok = ok;
        this.message = message;
    }

    public ScheduleResponse(boolean ok, String message, ArrayList<Schedule> schedule) {
        this.ok = ok;
        this.message = message;
        this.schedule = schedule;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPageby() {
        return pageby;
    }

    public void setPageby(int pageby) {
        this.pageby = pageby;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
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

    public ArrayList<Schedule> getSchedule() {
        return schedule;
    }

    public void setSchedule(ArrayList<Schedule> schedule) {
        this.schedule = schedule;
    }

    public static ScheduleResponse createAddEditDelete(boolean ok, String message) {
        return new ScheduleResponse(ok, message);
    }

    public static ScheduleResponse createFetch(boolean ok, String message, ArrayList<Schedule> schedule,ScheduleRequest req) {
        ScheduleResponse res =  new ScheduleResponse(ok, message,
                Utils.slice(schedule,req.getPage(),req.getPageby()));
       res.setTotal(schedule.size());
       res.setPage(req.getPage());
       res.setPageby(req.getPageby());
        return res;
    }

}
