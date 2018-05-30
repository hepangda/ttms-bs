package com.hepangda.ttms.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.annotation.JSONField;
import com.hepangda.ttms.model.CheckOriginPasswordRequest;
import com.hepangda.ttms.model.CheckOriginPasswordResponse;
import com.hepangda.ttms.model.Employee;
import com.hepangda.ttms.service.LoginService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/api/checkPassword")
public class CheckOriginPasswordServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Employee currentUser = (Employee)session.getAttribute("currentUser");

        CheckOriginPasswordRequest copr = new CheckOriginPasswordRequest(
                currentUser.getLoginName(), req.getParameter("pwd"));
        System.out.println(req.getParameter("pwd"));
        CheckOriginPasswordResponse cops = LoginService.checkOriginPassword(copr);

        resp.getWriter().println(JSON.toJSONString(cops));
    }
}
