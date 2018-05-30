package com.hepangda.ttms.controller;


import com.alibaba.fastjson.JSON;
import com.hepangda.ttms.model.CheckLoginResponse;
import com.hepangda.ttms.model.Employee;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/api/checkLogin")
public class CheckLoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Employee currentUser = (Employee)session.getAttribute("currentUser");

        CheckLoginResponse clr = new CheckLoginResponse(true, currentUser.getName(), currentUser.getPrivilege());

        resp.getWriter().println(JSON.toJSONString(clr));
    }
}
