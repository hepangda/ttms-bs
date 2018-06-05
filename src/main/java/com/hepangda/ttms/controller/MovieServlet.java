package com.hepangda.ttms.controller;

import com.alibaba.fastjson.JSON;
import com.hepangda.ttms.model.dto.MovieRequest;
import com.hepangda.ttms.model.dto.MovieResponse;
import com.hepangda.ttms.service.MovieService;
import com.hepangda.ttms.util.ExtendedServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/api/movie")
public class MovieServlet extends ExtendedServlet {
    private MovieRequest getUR(HttpServletRequest req, RequestType type) {
        return super.getUR(req, type, MovieRequest.class);
    }
    @Override//add
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        MovieRequest ureq = getUR(req, RequestType.POST);
        MovieResponse ures = MovieService.add(req.getSession(), ureq);
        resp.getWriter().println(JSON.toJSONString(ures));
    }

    @Override//edit
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        MovieRequest ureq = getUR(req, RequestType.PUT);
        MovieResponse ures = MovieService.edit(req.getSession(), ureq);
        resp.getWriter().println(JSON.toJSONString(ures));
    }

    @Override//get
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        MovieRequest ureq = getUR(req, RequestType.GET);
        MovieResponse ures = MovieService.fetch(req.getSession(), ureq);
        resp.getWriter().println(JSON.toJSONString(ures));
    }

    @Override//delete
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        MovieRequest ureq = getUR(req, RequestType.DELETE);
        MovieResponse ures = MovieService.delete(req.getSession(), ureq);
        resp.getWriter().println(JSON.toJSONString(ures));
    }

    @Override
    protected void doOptions(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doOptions(req, resp);
    }
}
