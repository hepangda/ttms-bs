package com.hepangda.ttms.service;

import com.hepangda.ttms.idao.DAOFactory;
import com.hepangda.ttms.idao.IEmployeeDAO;
import com.hepangda.ttms.model.Employee;
import com.hepangda.ttms.model.dto.EmployeeRequest;
import com.hepangda.ttms.model.dto.EmployeeResponse;
import com.hepangda.ttms.util.Errno;
import com.hepangda.ttms.util.QueryResult;

import javax.servlet.http.HttpSession;

public class EmployeeService {
    public static EmployeeResponse add(HttpSession session, EmployeeRequest ureq) {
        IEmployeeDAO iedao = DAOFactory.createEmployeeDAO();
        int errno = iedao.add(ureq.getEmployees());

        iedao.close();
        return EmployeeResponse.createAddFetch(errno == 103, Errno.getMessage(errno));
    }

    public static EmployeeResponse modify(HttpSession sesion, EmployeeRequest ureq) {
        IEmployeeDAO iedao = DAOFactory.createEmployeeDAO();
        int errno = iedao.update(ureq.getEmployees());
        iedao.close();
        return EmployeeResponse.createAddFetch(errno == 106, Errno.getMessage(errno));
    }

    public static EmployeeResponse fetch(HttpSession sesion, EmployeeRequest ureq) {
        IEmployeeDAO iedao = DAOFactory.createEmployeeDAO();
        QueryResult<Employee> errno = iedao.query(ureq.getEmployees());
        iedao.close();
        return EmployeeResponse.createGet(errno.getRetno() == 104, Errno.getMessage(errno.getRetno()), errno.getResults(), ureq);
    }

    public static EmployeeResponse delete(HttpSession sesion, EmployeeRequest ureq) {
        IEmployeeDAO iedao = DAOFactory.createEmployeeDAO();
        int errno = iedao.delete(ureq.getEmployees().getId());
        iedao.close();
        return EmployeeResponse.createAddFetch(errno == 105, Errno.getMessage(errno));
    }
}