package com.hepangda.ttms.service;

import com.hepangda.ttms.idao.DAOFactory;
import com.hepangda.ttms.idao.IEmployeeDAO;
import com.hepangda.ttms.model.dto.LoginRequest;
import com.hepangda.ttms.model.dto.LoginResponse;

import javax.servlet.http.HttpSession;

public class LoginService {
    public static LoginResponse login(HttpSession session, LoginRequest ureq) {
        IEmployeeDAO dao = DAOFactory.createEmployeeDAO();
        int res = dao.verifyLoginInfo(ureq.getUsername(), ureq.getPassword());

        if (res == 1) {
            return LoginResponse.createLogin(true, "login success.");
        }

        return LoginResponse.createLogin(false, "nope");
    }

    public static LoginResponse logout(HttpSession session, LoginRequest ureq) {

    }

    public static LoginResponse checkLogin(HttpSession session, LoginRequest ureq) {

    }
}
