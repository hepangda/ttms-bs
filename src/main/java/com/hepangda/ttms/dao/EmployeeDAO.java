package com.hepangda.ttms.dao;

import com.hepangda.ttms.idao.IEmployeeDAO;
import com.hepangda.ttms.model.Employee;
import com.hepangda.ttms.util.QueryResult;

public class EmployeeDAO extends BaseDAO implements IEmployeeDAO {
    public QueryResult<Employee> verifyLoginInfo(String username, String password) {
        QueryResult<Employee> qr = query(
                new Employee(0, username, password, null, 0, null, 0)
        );

        if (qr.getResults().size() == 1) {
            qr.setRetno(101);
        } else if (qr.getRetno() != 102) {
            qr.setRetno(100);
        }

        return qr;
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
