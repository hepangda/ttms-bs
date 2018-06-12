package com.hepangda.ttms.controller;

import com.alibaba.fastjson.JSON;
import com.hepangda.ttms.model.dto.TicketRequest;
import com.hepangda.ttms.model.dto.TicketResponse;
import com.hepangda.ttms.service.TicketService;
import com.hepangda.ttms.service.TicketService;
import com.hepangda.ttms.util.ExtendedServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/api/ticket")
public class TicketServlet extends ExtendedServlet {
    private TicketRequest getUR(HttpServletRequest req, RequestType type) {
        return super.getUR(req, type, TicketRequest.class);
    }

    @Override//add
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        TicketRequest ureq = getUR(req, RequestType.POST);
        TicketResponse ures = TicketService.add(req.getSession(), ureq);
        resp.getWriter().println(JSON.toJSONString(ures));
    }

    @Override//edit
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        TicketRequest ureq = getUR(req, RequestType.PUT);
        TicketResponse ures = TicketService.edit(req.getSession(), ureq);
        resp.getWriter().println(JSON.toJSONString(ures));
    }

    @Override//get
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        TicketRequest ureq = getUR(req, RequestType.GET);
        TicketResponse ures = TicketService.fetch(req.getSession(), ureq);
        resp.getWriter().println(JSON.toJSONString(ures));
    }

    @Override//delete
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        TicketRequest ureq = getUR(req, RequestType.DELETE);
        TicketResponse ures = TicketService.delete(req.getSession(), ureq);
        resp.getWriter().println(JSON.toJSONString(ures));
    }

    protected void doOptions(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doOptions(req, resp);
    }
}
