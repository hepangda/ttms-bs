package com.hepangda.ttms.model;

import com.alibaba.fastjson.annotation.JSONField;

public class AddEmployeeRequest {
    @JSONField(name = "name")
    private String name;
    @JSONField(name = "loginName")
    private String loginName;
    @JSONField(name = "password")
    private String password;
    @JSONField(name = "bornYear")
    private int bornYear;
    @JSONField(name = "phoneNumber")
    private String phoneNumber;
    @JSONField(name = "privilege")
    private short privilege;

    public AddEmployeeRequest(String name, String loginName, String password, int bornYear, String phoneNumber, short privilege) {
        this.name = name;
        this.loginName = loginName;
        this.password = password;
        this.bornYear = bornYear;
        this.phoneNumber = phoneNumber;
        this.privilege = privilege;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getBornYear() {
        return bornYear;
    }

    public void setBornYear(int bornYear) {
        this.bornYear = bornYear;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public short getPrivilege() {
        return privilege;
    }

    public void setPrivilege(short privilege) {
        this.privilege = privilege;
    }
}
