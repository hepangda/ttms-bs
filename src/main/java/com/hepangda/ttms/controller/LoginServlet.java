package com.hepangda.ttms.controller;

import com.alibaba.fastjson.JSON;
import com.hepangda.ttms.model.Employee;
import com.hepangda.ttms.model.dto.LoginRequest;
import com.hepangda.ttms.model.dto.LoginResponse;
import com.hepangda.ttms.service.LoginService;
import com.hepangda.ttms.util.ExtendedServlet;
import sun.misc.Request;
import sun.rmi.runtime.Log;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
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
public class LoginServlet extends ExtendedServlet {
    private LoginRequest getUR(HttpServletRequest req, RequestType type) {
        return super.getUR(req, type, LoginRequest.class);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LoginRequest ureq = getUR(req, RequestType.DELETE);
        LoginResponse ures = LoginService.logout(req.getSession(), ureq);
        resp.getWriter().println(JSON.toJSONString(ures));
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LoginRequest ureq = getUR(req, RequestType.POST);
        LoginResponse ures = LoginService.checkLogin(req.getSession(), ureq);
        resp.getWriter().println(JSON.toJSONString(ures));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LoginRequest ureq = getUR(req, RequestType.POST);
        LoginResponse ures = LoginService.login(req.getSession(), ureq);
        resp.getWriter().println(JSON.toJSONString(ures));
    }
}