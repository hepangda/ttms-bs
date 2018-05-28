package com.hepangda.ttms.controller;

import com.alibaba.fastjson.JSON;
import com.hepangda.ttms.model.LoginInfo;
import com.hepangda.ttms.model.LoginResult;
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
        res.setCharacterEncoding("UTF-8");
        HttpSession session = req.getSession();
        LoginInfo info = new LoginInfo(
            req.getParameter("username"), req.getParameter("password")
        );

        session.setAttribute("info", info);
        LoginResult ret = LoginService.login(info);
        res.getWriter().println(JSON.toJSONString(ret));
    }
}