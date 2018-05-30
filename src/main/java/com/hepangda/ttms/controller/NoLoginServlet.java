package com.hepangda.ttms.controller;

import com.alibaba.fastjson.JSON;
import com.hepangda.ttms.model.CheckLoginResponse;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/nologin")
public class NoLoginServlet extends HttpServlet {
    private static CheckLoginResponse clr = new CheckLoginResponse(false, "", (short)0);
    private static String repStr = JSON.toJSONString(clr);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.getWriter().println(repStr);
    }
}
