package com.hepangda.ttms.controller;

import com.alibaba.fastjson.JSON;
import com.hepangda.ttms.model.dto.MovieRequest;
import com.hepangda.ttms.model.dto.ScheduleRequest;
import com.hepangda.ttms.model.dto.ScheduleResponse;
import com.hepangda.ttms.service.ScheduleService;
import com.hepangda.ttms.util.ExtendedServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.rmi.ServerException;

@WebServlet("/api/schedule")
public class ScheduleServlet extends ExtendedServlet {
    private ScheduleRequest getUR(HttpServletRequest req, RequestType type) {
        return super.getUR(req, type, ScheduleRequest.class);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServerException, IOException {
        ScheduleRequest ureq = getUR(req, RequestType.POST);
        ScheduleResponse ures = ScheduleService.add(req.getSession(), ureq);
        resp.getWriter().println(JSON.toJSONString(ures));

    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServerException, IOException {
        ScheduleRequest ureq = getUR(req, RequestType.PUT);
        ScheduleResponse ures = ScheduleService.edit(req.getSession(), ureq);
        resp.getWriter().println(JSON.toJSONString(ures));

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServerException, IOException {
        ScheduleRequest ureq = getUR(req, RequestType.GET);
        ScheduleResponse ures = ScheduleService.fetch(req.getSession(), ureq);
        resp.getWriter().println(JSON.toJSONString(ures));

    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServerException, IOException {
        ScheduleRequest ureq = getUR(req, RequestType.DELETE);
        ScheduleResponse ures = ScheduleService.delete(req.getSession(), ureq);
        resp.getWriter().println(JSON.toJSONString(ures));
    }

    @Override
    protected void doOptions(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doOptions(req, resp);
    }
}
