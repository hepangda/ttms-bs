package com.hepangda.ttms.service;

import com.hepangda.ttms.dao.EmployeeDAO;
import com.hepangda.ttms.model.LoginInfo;

public class LoginService {
    public static int login(LoginInfo info) {
        return EmployeeDAO.verifyLoginInfo(info.getLoginName(), info.getPassword());
    }
}
