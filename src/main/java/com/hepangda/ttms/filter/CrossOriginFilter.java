package com.hepangda.ttms.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter("/*")
public class CrossOriginFilter implements Filter {
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        //设置跨域请求 ,
        HttpServletResponse response = (HttpServletResponse) res;
        response.setHeader("Access-Control-Allow-Origin", "http://192.168.1.136:4444");
        response.setHeader("Access-Control-Allow-Methods", "POST,GET,DELETE,PUT,OPTIONS");
        response.setHeader("Access-Control-Max-Age", "3628800");
        response.setHeader("Access-Control-Allow-Headers", "Origin,X-Requested-With,Content-Type,Accept");
        response.setHeader("Access-Control-Allow-Credentials", "true");


        chain.doFilter(req, response);
    }
}
