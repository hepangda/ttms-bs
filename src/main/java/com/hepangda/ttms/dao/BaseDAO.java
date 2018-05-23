package com.hepangda.ttms.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class BaseDAO {
    private static final String DB_USERNAME = "root";
    private static final String DB_PASSWORD = "980217";
    private static final String DB_CONNSTR = "jdbc:mysql://localhost:3306/ttms?serverTimezone=UTC";

    private Connection _Conn;
    protected BaseDAO() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            _Conn = DriverManager.getConnection(DB_CONNSTR, DB_USERNAME, DB_PASSWORD);
        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            System.err.println("Cannot found MySQL/JDBC Driver.");
            ex.printStackTrace();
        }
    }

    protected Statement getStatement() throws SQLException {
        return _Conn.createStatement();
    }
}
