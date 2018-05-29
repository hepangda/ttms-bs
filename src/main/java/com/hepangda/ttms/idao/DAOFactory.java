package com.hepangda.ttms.idao;

import com.hepangda.ttms.dao.EmployeeDAO;
import com.hepangda.ttms.dao.StudioDAO;

public class DAOFactory {
    private DAOFactory() {}

    public static IEmployeeDAO createEmployeeDAO() {
        return new EmployeeDAO();
    }

    public static IStudioDAO createStudioDAO() {
        return new StudioDAO();
    }

}
