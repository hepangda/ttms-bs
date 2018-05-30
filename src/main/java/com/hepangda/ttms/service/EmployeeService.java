package com.hepangda.ttms.service;

import com.hepangda.ttms.idao.DAOFactory;
import com.hepangda.ttms.model.AddEmployeeRequest;
import com.hepangda.ttms.model.AddEmployeeResponse;

public class EmployeeService {
    private static AddEmployeeResponse scAdd = new AddEmployeeResponse(true);
    private static AddEmployeeResponse frAdd = new AddEmployeeResponse(false);

    public static AddEmployeeResponse add(AddEmployeeRequest req) {
        int ret = DAOFactory.createEmployeeDAO().add(
                req.getName(), req.getLoginName(), req.getPassword(),
                req.getBornYear(), req.getPhoneNumber(), req.getPrivilege()
        );

        return ret == 0 ? scAdd : frAdd;
    }
}
