package com.hepangda.ttms.service;

import com.alibaba.fastjson.JSON;
import com.hepangda.ttms.idao.DAOFactory;
import com.hepangda.ttms.idao.IEmployeeDAO;
import com.hepangda.ttms.model.Employee;
import com.hepangda.ttms.model.dto.LoginRequest;
import com.hepangda.ttms.model.dto.LoginResponse;
import com.hepangda.ttms.util.Errno;
import com.hepangda.ttms.util.QueryResult;

import javax.servlet.http.HttpSession;

public class LoginService {
    public static LoginResponse login(HttpSession session, LoginRequest ureq) {
        IEmployeeDAO dao = DAOFactory.createEmployeeDAO();
        QueryResult<Employee> res = dao.verifyLoginInfo(ureq.getUsername(), ureq.getPassword());

        if (res.getRetno() == 101) {
            session.setAttribute("currentUser", res.getResults().get(0));
        }
        dao.close();
        return LoginResponse.createLoginLogout(res.getRetno() == 101, Errno.getMessage(res.getRetno()));
    }

    public static LoginResponse logout(HttpSession session, LoginRequest ureq) {
        session.invalidate();
        return LoginResponse.createLoginLogout(session.getAttribute("currentUser") != null, "You are now not logged yet.");
    }

    public static LoginResponse checkLogin(HttpSession session, LoginRequest ureq) {
        Employee currentUser = (Employee) session.getAttribute("currentUser");
        if (currentUser == null) {
            return LoginResponse.createCheckLogin(false, null);
        }
        return LoginResponse.createCheckLogin(true, currentUser.getId(), currentUser.getName(), currentUser.getPrivilege());
    }
}
