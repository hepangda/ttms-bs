package com.hepangda.ttms.util;

import java.util.HashMap;

public class Errno {
    private Errno() {}

    private static final HashMap<Integer, String> errnoMap = new HashMap<Integer, String>(){{
        put(100, "Username or password is incorrect.");
    }};

    public static String getMessage(int errno) {
        return errnoMap.get(errno);
    }
}
