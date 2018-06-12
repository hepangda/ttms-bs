package com.hepangda.ttms.controller;

import com.alibaba.fastjson.JSON;
import com.hepangda.ttms.model.dto.SeatRequest;
import com.hepangda.ttms.model.dto.SeatResponse;
import com.hepangda.ttms.service.SeatService;
import com.hepangda.ttms.util.ExtendedServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/api/seat")
public class SeatServlet extends ExtendedServlet {
    private SeatRequest getUR(HttpServletRequest req, RequestType type) {
        return super.getUR(req, type, SeatRequest.class);
    }

    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        SeatRequest ureq = getUR(req, RequestType.PUT);
        SeatResponse ures = SeatService.Update(req.getSession(), ureq);
        resp.getWriter().println(JSON.toJSONString(ures));
    }
    @Override
    protected void doOptions(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doOptions(req, resp);
    }
}