package com.hepangda.ttms.dao;

import com.hepangda.ttms.idao.IEmployeeDAO;

import java.sql.*;

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
public class EmployeeDAO extends BaseDAO /*implements IEmployeeDAO*/ {
    public static Connection _Conn;
    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            _Conn = DriverManager.getConnection(DB_CONNSTR, DB_USERNAME, DB_PASSWORD);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    public static ResultSet query(String queryString) throws SQLException {
        Statement st = _Conn.createStatement();
        return st.executeQuery(queryString);
    }

    public static int verifyLoginInfo(String username, String password) {
        int retval = 0;
        try {
            ResultSet rs = query("SELECT COUNT(*) FROM User WHERE Emp_LoginName=\'" + username
                    + "\' AND Emp_PASSWORD=md5(\'" + password + "\');");
            rs.next();
            retval = rs.getInt(1);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return retval;
    }
}
