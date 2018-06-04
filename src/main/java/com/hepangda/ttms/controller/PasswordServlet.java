package com.hepangda.ttms.controller;


import com.alibaba.fastjson.JSON;
import com.hepangda.ttms.model.*;
import com.hepangda.ttms.service.LoginService;
import com.hepangda.ttms.util.ExtendedServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/*
    PUT 更改
    GET 检查
 */

@WebServlet("/api/password")
public class PasswordServlet extends ExtendedServlet {
    @Override
    protected void doOptions(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doOptions(req, resp);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();

        ChangePasswordRequest cpr = new ChangePasswordRequest(
                -1, req.getParameter("oldpwd"), req.getParameter("newpwd")
        );
        System.out.println(cpr.getOldPassword());
        System.out.println(cpr.getNewPassword());
        String uid = req.getParameter("uid");
        if (uid != null) {
            cpr.setUid(Integer.valueOf(uid));
        } else {
            Employee currentUser = (Employee) session.getAttribute("currentUser");
            cpr.setUid(currentUser.getId());
        }

        System.out.println(JSON.toJSONString(cpr));
        ChangePasswordResponse cps = LoginService.changePassword(cpr);
        resp.getWriter().println(JSON.toJSONString(cps));
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Employee currentUser = (Employee) session.getAttribute("currentUser");

        CheckOriginPasswordRequest copr = new CheckOriginPasswordRequest(
                currentUser.getLoginName(), req.getParameter("pwd"));
        System.out.println(req.getParameter("pwd"));
        CheckOriginPasswordResponse cops = LoginService.checkOriginPassword(copr);

        resp.getWriter().println(JSON.toJSONString(cops));
    }
}
