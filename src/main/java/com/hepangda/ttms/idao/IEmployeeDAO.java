package com.hepangda.ttms.idao;

import com.hepangda.ttms.model.Employee;
import com.hepangda.ttms.util.QueryResult;

import java.util.ArrayList;

public interface IEmployeeDAO {
    QueryResult<Employee> verifyLoginInfo(String username, String password);

    int add(String loginName, String name, String password, int bornYear, String phoneNumber, int privilege);

    int add(Employee emp);

    int delete(int id);

    int delete(Employee emp);

    QueryResult<Employee> query(Employee ek);

    int update(Employee emp);
}