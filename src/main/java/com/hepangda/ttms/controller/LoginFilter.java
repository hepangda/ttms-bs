package com.hepangda.ttms.controller;

import com.hepangda.ttms.model.LoginInfo;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("/api/*")
public class LoginFilter implements Filter {
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest)servletRequest;
        HttpServletResponse res = (HttpServletResponse)servletResponse;
        HttpSession ss = req.getSession();
        LoginInfo info = (LoginInfo)ss.getAttribute("info");
        if (info == null) {
            res.sendRedirect("/nologin");
            return;
        }
        System.out.println(info.getLoginName());
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
