package com.hepangda.ttms.model;

import com.alibaba.fastjson.annotation.JSONField;

public class CheckLoginResponse {
    @JSONField(name = "loged")
    private boolean loged;
    @JSONField(name = "name")
    private String name;
    @JSONField(name = "privilege")
    private short privilege;

    public CheckLoginResponse(boolean loged, String name, short privilege) {
        this.loged = loged;
        this.name = name;
        this.privilege = privilege;
    }

    public boolean isLoged() {
        return loged;
    }

    public void setLoged(boolean loged) {
        this.loged = loged;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public short getPrivilege() {
        return privilege;
    }

    public void setPrivilege(short privilege) {
        this.privilege = privilege;
    }
}
