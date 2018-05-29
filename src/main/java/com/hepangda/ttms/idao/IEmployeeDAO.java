package com.hepangda.ttms.idao;

import com.hepangda.ttms.model.Employee;

import java.util.ArrayList;

public interface IEmployeeDAO {
//    int AddEmployee(String name,int age,int phone,String position,String password);
//    int DeleteEmployee(int ID);
//    int DeleteEmployee(String name);
    int verifyLoginInfo(String username, String password);
    ArrayList<Employee> queryById(int id);
    ArrayList<Employee> queryByName(String name);
    ArrayList<Employee> queryByLoginName(String loginName);
}
