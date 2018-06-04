package com.hepangda.ttms.model;

import com.alibaba.fastjson.annotation.JSONField;

public class Employee {
    @JSONField(name = "uid")
    private int id;
    @JSONField(name = "loginname")
    private String loginName;
    @JSONField(name = "name")
    private String name;
    @JSONField(name = "bornyear")
    private int bornYear;
    @JSONField(name = "phonenumber")
    private String phoneNumber;
    @JSONField(name = "privilege")
    private int privilege;

    public Employee(int id, String loginName, String name, int bornYear, String phoneNumber, int privilege) {
        this.id = id;
        this.loginName = loginName;
        this.name = name;
        this.bornYear = bornYear;
        this.phoneNumber = phoneNumber;
        this.privilege = privilege;
    }

    public Employee(String loginName, String name, int bornYear, String phoneNumber, int privilege) {
        this.loginName = loginName;
        this.name = name;
        this.bornYear = bornYear;
        this.phoneNumber = phoneNumber;
        this.privilege = privilege;
    }

    public Employee() {

    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBornYear() {
        return bornYear;
    }

    public void setBornYear(int bornYear) {
        this.bornYear = bornYear;
    }

    public int getPrivilege() {
        return privilege;
    }

    public void setPrivilege(int privilege) {
        this.privilege = privilege;
    }
}
