package com.hepangda.ttms.controller;

import com.alibaba.fastjson.JSON;
import com.hepangda.ttms.model.ChangePasswordRequest;
import com.hepangda.ttms.model.ChangePasswordResponse;
import com.hepangda.ttms.model.Employee;
import com.hepangda.ttms.service.LoginService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/api/password/change")
public class ChangePasswordServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();

        ChangePasswordRequest cpr = new ChangePasswordRequest(
                -1, req.getParameter("oldpwd"), req.getParameter("newpwd")
        );

        String uid = req.getParameter("uid");
        if (uid != null) {
            cpr.setUid(Integer.valueOf(uid));
        } else {
            Employee currentUser = (Employee)session.getAttribute("currentUser");
            cpr.setUid(currentUser.getId());
        }

        System.out.println(JSON.toJSONString(cpr));
        ChangePasswordResponse cps = LoginService.changePassword(cpr);
        resp.getWriter().println(JSON.toJSONString(cps));
    }
}
