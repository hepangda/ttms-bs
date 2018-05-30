package com.hepangda.ttms.model;
public class Employee {
    private int id;
    private String loginName;
    private String name;
    private int bornYear;
    private String phoneNumber;
    private short privilege;

    public Employee(int id, String loginName, String name, int bornYear, String phoneNumber, short privilege) {
        this.id = id;
        this.loginName = loginName;
        this.name = name;
        this.bornYear = bornYear;
        this.phoneNumber = phoneNumber;
        this.privilege = privilege;
    }

    public Employee(String loginName, String name, int bornYear, String phoneNumber, short privilege) {
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

    public short getPrivilege() {
        return privilege;
    }

    public void setPrivilege(short privilege) {
        this.privilege = privilege;
    }
}
