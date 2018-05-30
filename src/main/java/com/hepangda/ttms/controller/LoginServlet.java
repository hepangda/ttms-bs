package com.hepangda.ttms.controller;

import com.alibaba.fastjson.JSON;
import com.hepangda.ttms.model.LoginRequest;
import com.hepangda.ttms.model.LoginResponse;
import com.hepangda.ttms.service.LoginService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        doPost(req, res);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        HttpSession session = req.getSession();
        LoginRequest info = new LoginRequest(
            req.getParameter("username"), req.getParameter("password")
        );

        LoginResponse ret = LoginService.login(info);
        if (ret.isOk()) {
            session.setAttribute("currentUser", ret.getLogUser());
        }

        res.getWriter().println(JSON.toJSONString(ret));
    }
}