package com.hepangda.ttms.util;

import com.alibaba.fastjson.JSON;
import com.sun.deploy.net.HttpRequest;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStreamReader;

public class ExtendedServlet extends HttpServlet {
    protected enum RequestType {
        GET,
        POST,
        PUT,
        DELETE,
    }

    private final String GET_JSON_NAME = "JSONV";


    protected <T> T getUR(HttpServletRequest req, RequestType rt, Class<T> cls) {
        try {
            switch (rt) {
                case GET:
                    return JSON.parseObject(req.getParameter(GET_JSON_NAME), cls);
                case POST: case PUT: case DELETE:
                    return JSON.parseObject(Utils.read(new InputStreamReader(req.getInputStream())), cls);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return null;
    }
}