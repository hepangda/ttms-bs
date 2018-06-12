package com.hepangda.ttms.controller;

import com.alibaba.fastjson.JSON;
import com.hepangda.ttms.model.dto.FoodRequest;
import com.hepangda.ttms.model.dto.FoodResponse;
import com.hepangda.ttms.service.FoodService;
import com.hepangda.ttms.util.ExtendedServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/api/foods")
public class FoodServlet extends ExtendedServlet {
    private FoodRequest getUR(HttpServletRequest req, ExtendedServlet.RequestType type) {
        return super.getUR(req, type, FoodRequest.class);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        FoodRequest ureq = getUR(req, ExtendedServlet.RequestType.POST);
        FoodResponse ures = FoodService.add(req.getSession(), ureq);
        resp.getWriter().println(JSON.toJSONString(ures));
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        FoodRequest ureq = getUR(req, ExtendedServlet.RequestType.GET);
        FoodResponse ures = FoodService.fetch(req.getSession(), ureq);
        resp.getWriter().println(JSON.toJSONString(ures));
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        FoodRequest ureq = getUR(req, ExtendedServlet.RequestType.DELETE);
        FoodResponse ures = FoodService.delete(req.getSession(), ureq);
        resp.getWriter().println(JSON.toJSONString(ures));
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        FoodRequest ureq = getUR(req, ExtendedServlet.RequestType.PUT);
        FoodResponse ures = FoodService.edit(req.getSession(), ureq);
        resp.getWriter().println(JSON.toJSONString(ures));
    }
}
