package com.hepangda.ttms.controller;

import com.alibaba.fastjson.JSON;

import com.hepangda.ttms.model.dto.StudioRequest;
import com.hepangda.ttms.model.dto.StudioResponse;

import com.hepangda.ttms.service.StudioService;
import com.hepangda.ttms.util.ExtendedServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/api/studio")
public class StudioServlet extends ExtendedServlet {
    private StudioRequest getUR(HttpServletRequest req, RequestType type) {
        return super.getUR(req, type, StudioRequest.class);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        StudioRequest ureq = getUR(req, RequestType.POST);
        StudioResponse ures = StudioService.add(req.getSession(), ureq);
        resp.getWriter().println(JSON.toJSONString(ures));
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        StudioRequest ureq = getUR(req, RequestType.GET);
        System.out.println(JSON.toJSONString(ureq));
        StudioResponse ures = StudioService.fetch(req.getSession(), ureq);
        resp.getWriter().println(JSON.toJSONString(ures));
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        StudioRequest ureq = getUR(req, RequestType.DELETE);
        StudioResponse ures = StudioService.delete(req.getSession(), ureq);
        resp.getWriter().println(JSON.toJSONString(ures));
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        StudioRequest ureq = getUR(req, RequestType.PUT);
        StudioResponse ures = StudioService.edit(req.getSession(), ureq);
        resp.getWriter().println(JSON.toJSONString(ures));
    }
}
