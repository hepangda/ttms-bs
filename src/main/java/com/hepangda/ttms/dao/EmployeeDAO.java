package com.hepangda.ttms.dao;

import com.hepangda.ttms.idao.IEmployeeDAO;
import com.hepangda.ttms.model.Employee;

import java.sql.*;
import java.util.ArrayList;

/*
CREATE TABLE Employee (
    Emp_ID SERIAL PRIMARY KEY,
    Emp_LoginName VARCHAR(30) NOT NULL UNIQUE,
    Emp_Password CHAR(32) NOT NULL,
    Emp_Name VARCHAR(30) NOT NULL,
    Emp_BornYear SMALLINT NOT NULL,
    Emp_Phonenumber VARCHAR(14),
    Emp_Privilege TINYINT NOT NULL DEFAULT 0
);

CREATE VIEW User AS
    SELECT Emp_ID,Emp_LoginName,Emp_Password,Emp_Privilege FROM Employee;
*/
public class EmployeeDAO extends BaseDAO implements IEmployeeDAO {
    public int verifyLoginInfo(String username, String password) {
        int retval = 0;
        try {
            PreparedStatement stmt = super.getStatement(
                    "SELECT COUNT(*) FROM User WHERE Emp_LoginName=? AND Emp_Password=md5(?);"
            );

            stmt.setString(1, username);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();

            rs.next();//不然什么数据都没有
            retval = rs.getInt(1);
        } catch (Exception ex) {
            ex.printStackTrace();
            return 1;
        }
        return 2;
    }

    private ArrayList<Employee> queryWhere(String cond) {
        ArrayList<Employee> res = new ArrayList<>();

        try {
            Statement stmt = super.getPureStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Employee WHERE " + cond + ";");

            while (rs.next()) {
                res.add(new Employee(
                        rs.getInt("Emp_ID"), rs.getString("Emp_LoginName"),
                        rs.getString("Emp_Name"), rs.getInt("Emp_BornYear"),
                        rs.getString("Emp_PhoneNumber"), rs.getShort("Emp_Privilege")
                ));
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return res;
    }

    @Override
    public ArrayList<Employee> queryById(int id) {
        return queryWhere("Emp_ID=" + id);
    }

    @Override
    public ArrayList<Employee> queryByLoginName(String loginName) {
        return queryWhere("Emp_LoginName=\'" + loginName + "\'");
    }

    @Override
    public ArrayList<Employee> queryByName(String name) {
        return queryWhere("Emp_Name=\'" + name + "\';");
    }

    @Override
    public int changePassword(int id, String oldPassword, String newPassword) {
        if (queryWhere("Emp_ID=" + id + " AND Emp_Password=md5(\'" + oldPassword + "\')").isEmpty()) {
            return -1;
        }

        try {
            PreparedStatement stmt = super.getStatement(
                    "UPDATE Employee SET Emp_Password=md5(?) WHERE Emp_ID=?;"
            );

            stmt.setString(1, newPassword);
            stmt.setInt(2, id);

            int rs = stmt.executeUpdate();

            if (rs > 0)
                return 0;
            return -2;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return -3;
    }

    @Override
    public int add(String loginName, String name, String password, int bornYear, String phoneNumber, short privilege) {
        try {
            PreparedStatement stmt = super.getStatement(
                    "INSERT INTO Employee VALUES(default,?,md5(?),?,?,?,?);"
            );

            stmt.setString(1, loginName);
            stmt.setString(2, password);
            stmt.setString(3, name);
            stmt.setInt(4, bornYear);
            stmt.setString(5, phoneNumber);
            stmt.setInt(6, (int)privilege);

            int rs = stmt.executeUpdate();

            if (rs > 0)
                return 0;
            return -1;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return -2;
    }

}
