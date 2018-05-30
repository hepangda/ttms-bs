package com.hepangda.ttms.service;

import com.hepangda.ttms.idao.DAOFactory;
import com.hepangda.ttms.idao.IEmployeeDAO;
import com.hepangda.ttms.model.*;

public class LoginService {
    private static LoginResponse frLogin = new LoginResponse(false, new Employee());
    private static CheckOriginPasswordResponse frCheckOriginPassword = new CheckOriginPasswordResponse(false);
    private static CheckOriginPasswordResponse scCheckOriginPassword = new CheckOriginPasswordResponse(true);
    private static ChangePasswordResponse frChangePassword = new ChangePasswordResponse(false);
    private static ChangePasswordResponse scChangePassword = new ChangePasswordResponse(true);

    public static LoginResponse login(LoginRequest info) {
        IEmployeeDAO iedao = DAOFactory.createEmployeeDAO();
        int res = iedao.verifyLoginInfo(info.getLoginName(), info.getPassword());

        if (res == 1) {
            return new LoginResponse(true, iedao.queryByLoginName(info.getLoginName()).get(0));
        }

        return frLogin;
    }

    public static CheckOriginPasswordResponse checkOriginPassword(CheckOriginPasswordRequest req) {
        LoginResponse res = login(new LoginRequest(req.getUsername(), req.getTryPassword()));
        return res.isOk() ? scCheckOriginPassword : frCheckOriginPassword;
    }

    public static ChangePasswordResponse changePassword(ChangePasswordRequest req) {
        int res = DAOFactory.createEmployeeDAO().changePassword(
                req.getUid(), req.getOldPassword(), req.getNewPassword()
        );

        return res == 0 ? scChangePassword : frChangePassword;
    }
}
