package com.hepangda.ttms;

import com.alibaba.fastjson.JSON;
import com.hepangda.ttms.model.AddEmployeeRequest;
import com.hepangda.ttms.model.AddEmployeeResponse;
import com.hepangda.ttms.service.EmployeeService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/api/employee/add")
public class AddEmployeeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        AddEmployeeRequest aer = new AddEmployeeRequest(
                req.getParameter("name"), req.getParameter("loginName"),
                req.getParameter("password"), Integer.valueOf(req.getParameter("bornYear")),
                req.getParameter("phoneNumber"), Short.valueOf(req.getParameter("privilege"))
        );

        AddEmployeeResponse aes = EmployeeService.add(aer);

        resp.getWriter().println(JSON.toJSONString(aes));
    }
}
