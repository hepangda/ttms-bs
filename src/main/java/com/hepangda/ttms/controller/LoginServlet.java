package com.hepangda.ttms.controller;


import com.hepangda.ttms.model.LogUser;
import com.hepangda.ttms.model.LoginInfo;
import com.hepangda.ttms.service.LoginService;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        res.setCharacterEncoding("UTF-8");

        LoginInfo info = new LoginInfo(
            req.getParameter("username"), req.getParameter("password")
        );

        int ret = LoginService.login(info);
        if (ret == 0) {
            res.getWriter().println("{\"result\": false}");
        } else {
            res.getWriter().println("{\"result\": true}");
        }
    }
}