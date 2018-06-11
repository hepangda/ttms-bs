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
        if (errno == 103) {
            return EmployeeResponse.createAddFetch(true, Errno.getMessage(errno));
        }

        return EmployeeResponse.createAddFetch(false, Errno.getMessage(errno));
    }

    public static EmployeeResponse modify(HttpSession sesion, EmployeeRequest ureq) {
        return null;
    }

    public static EmployeeResponse fetch(HttpSession sesion, EmployeeRequest ureq) {
        IEmployeeDAO iedao = DAOFactory.createEmployeeDAO();
        QueryResult<Employee> errno = iedao.query(ureq.getEmployees());
        if (errno.getRetno() == 104) {
            return EmployeeResponse.createGet(true, Errno.getMessage(errno.getRetno()), errno.getResults(), ureq);
        }
        return EmployeeResponse.createGet(false, Errno.getMessage(errno.getRetno()), errno.getResults(), ureq);
    }

    public static EmployeeResponse delete(HttpSession sesion, EmployeeRequest ureq) {
        return null;
    }
}