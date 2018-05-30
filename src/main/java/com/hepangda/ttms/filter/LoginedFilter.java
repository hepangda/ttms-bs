package com.hepangda.ttms.filter;

import com.hepangda.ttms.model.Employee;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("/api/*")
public class LoginedFilter implements Filter {
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest)servletRequest;
        HttpServletResponse res = (HttpServletResponse)servletResponse;
        HttpSession ss = req.getSession();

        Employee info = (Employee) ss.getAttribute("currentUser");
        if (info == null) {
            res.sendRedirect("/nologin");
            return;
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }
}
