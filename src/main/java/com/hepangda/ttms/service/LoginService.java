package com.hepangda.ttms.service;

import com.hepangda.ttms.idao.DAOFactory;
import com.hepangda.ttms.idao.IEmployeeDAO;
import com.hepangda.ttms.model.Employee;
import com.hepangda.ttms.model.dto.LoginRequest;
import com.hepangda.ttms.model.dto.LoginResponse;
import com.hepangda.ttms.util.Errno;

import javax.servlet.http.HttpSession;

public class LoginService {
    public static LoginResponse login(HttpSession session, LoginRequest ureq) {
        IEmployeeDAO dao = DAOFactory.createEmployeeDAO();
        int res = dao.verifyLoginInfo(ureq.getUsername(), ureq.getPassword());

        if (res == 101) {
            return LoginResponse.createLoginLogout(true, Errno.getMessage(res));
        }

        return LoginResponse.createLoginLogout(false, Errno.getMessage(res));
    }

    public static LoginResponse logout(HttpSession session, LoginRequest ureq) {
        if (session.getAttribute("currentUser") == null) {
            return LoginResponse.createLoginLogout(false, "You are not logged in.");
        }

        return LoginResponse.createLoginLogout(true, "You are now not logged yet.");
    }

    public static LoginResponse checkLogin(HttpSession session, LoginRequest ureq) {
        Employee currentUser = (Employee) session.getAttribute("currentUser");
        if (currentUser == null) {
            return LoginResponse.createCheckLogin(false, null);
        }
        return LoginResponse.createCheckLogin(true, currentUser.getId(), currentUser.getName(), currentUser.getPrivilege());
    }
}
