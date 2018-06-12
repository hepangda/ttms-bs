package com.hepangda.ttms.model;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.annotation.JSONField;
import com.hepangda.ttms.annotation.QueryKey;
import com.hepangda.ttms.annotation.QueryTable;

@QueryTable("Employee")
public class Employee {
    @JSONField(name = "uid")
    @QueryKey(value = "Emp_ID", insert = false, delete = true, primaryKey = true)
    private int id;

    @JSONField(name = "loginname")
    @QueryKey("Emp_LoginName")
    private String loginName;

    @JSONField(name = "password")
    @QueryKey(value = "Emp_Password", select = false, specialInsert = true, insertString = "md5(?)")
    private String password;

    @JSONField(name = "name")
    @QueryKey("Emp_Name")
    private String name;

    @JSONField(name = "bornyear")
    @QueryKey("Emp_BornYear")
    private int bornYear;

    @JSONField(name = "phonenumber")
    @QueryKey("Emp_PhoneNumber")
    private String phoneNumber;

    @JSONField(name = "privilege")
    @QueryKey("Emp_Privilege")
    private int privilege;

    public Employee() {
    }

    public Employee(int id) {
        this.id = id;
    }

    public Employee(int id, String loginName, String password, String name, int bornYear, String phoneNumber, int privilege) {
        this.id = id;
        this.loginName = loginName;
        this.password = password;
        this.name = name;
        this.bornYear = bornYear;
        this.phoneNumber = phoneNumber;
        this.privilege = privilege;
    }

    public Employee(int id, String loginName, String name, int bornYear, String phoneNumber, int privilege) {
        this.id = id;
        this.loginName = loginName;
        this.name = name;
        this.bornYear = bornYear;
        this.phoneNumber = phoneNumber;
        this.privilege = privilege;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
