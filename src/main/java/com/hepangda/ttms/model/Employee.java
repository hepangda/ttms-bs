package com.hepangda.ttms.model;
public class Employee {
    int ID;
    String EmpLoginName;
    String EmpName;
    int BornYear;
    int Privilege;

//    public Employee() {
//
//    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public void setEmpLoginName(String empLoginName) {
        EmpLoginName = empLoginName;
    }

    public void setEmpName(String empName) {
        EmpName = empName;
    }

    public void setBornYear(int bornYear) {
        BornYear = bornYear;
    }

    public void setPrivilege(int privileg) {
        Privilege = privileg;
    }
}
