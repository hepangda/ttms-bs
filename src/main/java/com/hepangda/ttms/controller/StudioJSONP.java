package com.hepangda.ttms.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.annotation.JSONField;
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
import java.util.ArrayList;

@WebServlet("/api/jsonp/studio")
public class StudioJSONP extends ExtendedServlet {
    class JSONPResp {
        @JSONField(name = "result")
        public ArrayList<Studio> stus;
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        StudioRequest ureq = new StudioRequest();
        ureq.setType("Fetch");
        Studio stu = new Studio();
        stu.setName(req.getParameter("name"));
        ureq.setStudio(stu);
        StudioResponse ures = StudioService.fetch(req.getSession(), ureq);

        JSONPResp jpres = new JSONPResp();
        jpres.stus = ures.getStudios();
        resp.getWriter().println(req.getParameter("callback") + "(" + JSON.toJSONString(jpres) + ")");
    }
}
