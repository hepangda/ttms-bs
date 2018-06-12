package com.hepangda.ttms.controller;

import com.alibaba.fastjson.JSON;
import com.hepangda.ttms.model.dto.*;
import com.hepangda.ttms.service.SaleFoodsService;
import com.hepangda.ttms.util.ExtendedServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/api/salefood")
public class SaleFoodsServlet extends ExtendedServlet {
    private SaleFoodsRequest getUR(HttpServletRequest req, ExtendedServlet.RequestType type) {
        return super.getUR(req, type, SaleFoodsRequest.class);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        SaleFoodsRequest ureq = getUR(req, ExtendedServlet.RequestType.POST);
        SaleFoodsResponse ures = SaleFoodsService.sale(req.getSession() , ureq);
        resp.getWriter().println(JSON.toJSONString(ures));
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        SaleFoodsRequest ureq = getUR(req, ExtendedServlet.RequestType.GET);
        System.out.println(JSON.toJSONString(ureq));
        SaleFoodsResponse ures = SaleFoodsService.fetch(req.getSession(), ureq);
        resp.getWriter().println(JSON.toJSONString(ures));
    }

    @Override
    protected void doOptions(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doOptions(req, resp);
    }
}
