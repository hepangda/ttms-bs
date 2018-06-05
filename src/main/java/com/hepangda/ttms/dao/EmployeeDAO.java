package com.hepangda.ttms.dao;

import com.hepangda.ttms.annotation.QueryKey;
import com.hepangda.ttms.idao.IEmployeeDAO;
import com.hepangda.ttms.model.Employee;
import com.hepangda.ttms.util.QueryResult;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;

/*
CREATE TABLE Employee (
    Emp_ID SERIAL PRIMARY KEY,
    Emp_LoginName VARCHAR(30) NOT NULL UNIQUE,
    Emp_Password CHAR(32) NOT NULL,
    Emp_Name VARCHAR(30) NOT NULL,
    Emp_BornYear SMALLINT NOT NULL,
    Emp_Phonenumber VARCHAR(14),
    Emp_Privilege TINYINT NOT NULL DEFAULT 0
);

CREATE VIEW User AS
    SELECT Emp_ID,Emp_LoginName,Emp_Password,Emp_Privilege FROM Employee;
*/

public class EmployeeDAO extends BaseDAO implements IEmployeeDAO {
    public int verifyLoginInfo(String username, String password) {
        QueryResult<Employee> qr = query(
                new Employee(0, username, password, null, 0, null, 0)
        );

        if (qr.getRetno() == 102) {
            return 102;
        } else if (qr.getResults().size() == 1) {
            return 101;
        }
        return 100;
    }

    @Override
    public int add(String loginName, String name, String password, int bornYear, String phoneNumber, int privilege) {
        return add(new Employee(0, loginName, password, name, bornYear, phoneNumber, privilege));
    }

    @Override
    public int add(Employee emp) {
        return normalInsert(emp, 102, 103);
    }

    public QueryResult<Employee> query(Employee ek) {
        return normalSelect(ek, 102, 104);
    }

    @Override
    public int delete(int id) {
        return delete(new Employee(id));
    }

    @Override
    public int delete(Employee emp) {
        return normalDelete(emp, 102, 105);
    }

    @Override
    public int update(Employee emp) {
        return normalUpdate(emp, 102, 106);
    }
}
