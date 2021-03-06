package com.hepangda.ttms.model.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.hepangda.ttms.annotation.ValidateKey;
import com.hepangda.ttms.model.Studio;

public class StudioRequest {
    @ValidateKey(enums = {"Add", "Edit", "Fetch", "Delete"}, errno = 210)
    @JSONField(name = "type")
    private String type;
    @JSONField(name = "studio")
    private Studio studio;
    @JSONField(name = "page")
    private int page;
    @JSONField(name = "pageby")
    private int pageby;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Studio getStudio() {
        return studio;
    }

    public void setStudio(Studio studio) {
        this.studio = studio;
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

