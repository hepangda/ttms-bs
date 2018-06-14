package com.hepangda.ttms.controller;

import com.alibaba.fastjson.JSON;
import com.hepangda.ttms.model.Studio;
import com.hepangda.ttms.model.dto.StudioRequest;
import com.hepangda.ttms.model.dto.StudioResponse;
import com.hepangda.ttms.service.StudioService;
import com.hepangda.ttms.util.ExtendedServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/api/jsonp/studio")
public class StudioJSONP extends ExtendedServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        StudioRequest ureq = new StudioRequest();
        ureq.setType("Fetch");
        ureq.setPage(1);
        ureq.setPageby(20);
        Studio stu = new Studio();
        stu.setName(req.getParameter("name"));
        ureq.setStudio(stu);
        System.out.println(JSON.toJSONString(ureq));
        StudioResponse ures = StudioService.fetch(req.getSession(), ureq);
        System.err.println(JSON.toJSONString(ures));
        resp.getWriter().println(req.getParameter("callback") + "(" + JSON.toJSONString(ures) + ")");
    }
}
