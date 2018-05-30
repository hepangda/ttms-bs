package com.hepangda.ttms.controller;

import com.alibaba.fastjson.JSON;
import com.hepangda.ttms.model.CheckLoginResponse;
import com.hepangda.ttms.model.Employee;
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


/*
    Post 登录
    Get 是否登录
    Delete 登出
 */

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();

        if (session == null) {

        } else {
            session.invalidate();
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Employee currentUser = (Employee)session.getAttribute("currentUser");

        if (currentUser == null) {
            CheckLoginResponse clr = new CheckLoginResponse(false, "", (short)0);
            resp.getWriter().println(JSON.toJSONString(clr));
        } else {
            CheckLoginResponse clr = new CheckLoginResponse(true, currentUser.getName(), currentUser.getPrivilege());
            resp.getWriter().println(JSON.toJSONString(clr));
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        LoginRequest info = new LoginRequest(
            req.getParameter("username"), req.getParameter("password")
        );

        LoginResponse ret = LoginService.login(info);
        if (ret.isOk()) {
            session.setAttribute("currentUser", ret.getLogUser());
        }

        resp.getWriter().println(JSON.toJSONString(ret));
    }
}