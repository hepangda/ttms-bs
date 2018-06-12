package com.hepangda.ttms.util;

import com.alibaba.fastjson.JSON;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.net.URLDecoder;

public class ExtendedServlet extends HttpServlet {
    protected enum RequestType {
        GET,
        POST,
        PUT,
        DELETE,
    }

    private <T> T decodeObject(T ori) throws IllegalAccessException, UnsupportedEncodingException {
        Field[] fields = ori.getClass().getDeclaredFields();
        for (Field f : fields) {
            f.setAccessible(true);
            Class type = f.getType();
            if (!type.isPrimitive() && f.get(ori) != null) {
                if (type == String.class) {
                    f.set(ori, URLDecoder.decode((String) f.get(ori), "UTF-8"));
                } else {
                    f.set(ori, decodeObject(f.get(ori)));
                }
            }
        }
        return ori;
    }

    protected <T> T getUR(HttpServletRequest req, RequestType rt, Class<T> cls) {
        final String GET_JSON_NAME = "JSONV";
        T ori = null;
        try {
            switch (rt) {
                case GET:
                    ori = JSON.parseObject(URLDecoder.decode(req.getParameter(GET_JSON_NAME), "UTF-8"), cls);
                    break;
                case POST:
                case PUT:
                case DELETE:
                    ori = JSON.parseObject(Utils.read(new InputStreamReader(req.getInputStream())), cls);
            }
            System.out.println(JSON.toJSONString(ori));
            decodeObject(ori);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return ori;
    }
}
