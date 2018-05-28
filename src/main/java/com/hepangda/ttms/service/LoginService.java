package com.hepangda.ttms.service;

import com.hepangda.ttms.dao.EmployeeDAO;
import com.hepangda.ttms.idao.DAOFactory;
import com.hepangda.ttms.model.LoginInfo;
import com.hepangda.ttms.model.LoginResult;

public class LoginService {
    public static LoginResult login(LoginInfo info) {
        return new LoginResult(
                DAOFactory.createEmployeeDAO().verifyLoginInfo(info.getLoginName(), info.getPassword())
        );
    }
}
