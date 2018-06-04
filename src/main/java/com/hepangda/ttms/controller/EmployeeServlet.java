package com.hepangda.ttms.controller;

import com.alibaba.fastjson.JSON;
import com.hepangda.ttms.model.Employee;
import com.hepangda.ttms.model.dto.EmployeeRequest;
import com.hepangda.ttms.model.dto.EmployeeResponse;
import com.hepangda.ttms.model.dto.LoginRequest;
import com.hepangda.ttms.service.EmployeeService;
import com.hepangda.ttms.util.ExtendedServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/*
    POST 添加
 */

@WebServlet("/api/employee")
public class EmployeeServlet extends ExtendedServlet {
    private EmployeeRequest getUR(HttpServletRequest req, RequestType type) {
        return super.getUR(req, type, EmployeeRequest.class);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        EmployeeRequest ureq = getUR(req, RequestType.POST);
        EmployeeResponse ures = EmployeeService.add(req.getSession(), ureq);
        resp.getWriter().println(JSON.toJSONString(ures));
    }
}
