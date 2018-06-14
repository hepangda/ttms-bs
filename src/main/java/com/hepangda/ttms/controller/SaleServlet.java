package com.hepangda.ttms.controller;

import com.alibaba.fastjson.JSON;
import com.hepangda.ttms.model.dto.SalesTicketRequest;
import com.hepangda.ttms.model.dto.SalesTicketResponse;
import com.hepangda.ttms.model.dto.TicketRequest;
import com.hepangda.ttms.service.SaleTicketService;
import com.hepangda.ttms.util.ExtendedServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/api/sale")
public class SaleServlet extends ExtendedServlet {
    @Override
    protected void doOptions(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doOptions(req, resp);
    }

    private SalesTicketRequest getUR(HttpServletRequest req, RequestType type) {
        return super.getUR(req, type, SalesTicketRequest.class);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        SalesTicketRequest ureq = getUR(req, RequestType.POST);
        SalesTicketResponse ures = SaleTicketService.sale(req.getSession(), ureq);
        ures.setI(ureq.getTickets().getRow());
        ures.setJ(ureq.getTickets().getCol());
        resp.getWriter().println(JSON.toJSONString(ures));
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        SalesTicketRequest ureq = getUR(req, RequestType.DELETE);
        SalesTicketResponse ures = SaleTicketService.Return(req.getSession(), ureq);
        resp.getWriter().println(JSON.toJSONString(ures));
    }
}
