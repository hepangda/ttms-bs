package com.hepangda.ttms.controller;
import com.alibaba.fastjson.JSON;
import com.hepangda.ttms.model.dto.SalesTicketRequest;
import com.hepangda.ttms.model.dto.SalesTicketResponse;
import com.hepangda.ttms.service.SalesTicketService;
import com.hepangda.ttms.util.ExtendedServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/api/sale")
public class SalesTicketServlet extends ExtendedServlet {
    private SalesTicketRequest getUR(HttpServletRequest req, RequestType type){
        return super.getUR(req,type,SalesTicketRequest.class);
    }

    @Override//sale
    protected void doPost(HttpServletRequest req,HttpServletResponse resp) throws ServletException, IOException {
        SalesTicketRequest ureq = getUR(req,RequestType.POST);
        SalesTicketResponse ures = SalesTicketService.sale(req.getSession(),ureq);
        resp.getWriter().println(JSON.toJSONString(ures));
    }

    @Override//get
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        SalesTicketRequest ureq = getUR(req, RequestType.GET);
        SalesTicketResponse ures = SalesTicketService.fetch(req.getSession(), ureq);
        resp.getWriter().println(JSON.toJSONString(ures));
    }

    @Override//Return
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        SalesTicketRequest ureq = getUR(req, RequestType.DELETE);
        SalesTicketResponse ures = SalesTicketService.Return(req.getSession(), ureq);
        resp.getWriter().println(JSON.toJSONString(ures));
    }
    protected void doOptions(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doOptions(req, resp);
    }
}