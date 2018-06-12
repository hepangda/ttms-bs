package com.hepangda.ttms.controller;

import com.hepangda.ttms.util.ExtendedServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

@WebServlet("/test")
public class TestServlet extends ExtendedServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<String, String[]> a = req.getParameterMap();
        for (String p : a.keySet()) {
            System.out.println(p);
            for (String v : a.get(p)) {
                System.out.println(v);
            }
        }
        System.out.println(req.getParameter("JSONV"));
    }
}
