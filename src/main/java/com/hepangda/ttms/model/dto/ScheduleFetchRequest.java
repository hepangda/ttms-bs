package com.hepangda.ttms.model.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.hepangda.ttms.model.ScheduleFetch;

public class ScheduleFetchRequest {
    @JSONField(name="type")
    private String type;

    @JSONField(name="schedule")
    private ScheduleFetch schedule;

    @JSONField(name ="page")
    private int page;

    @JSONField(name = "pageby")
    private int pageby;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public ScheduleFetch getSchedule() {
        return schedule;
    }

    public void setSchedule(ScheduleFetch schedule) {
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
}
