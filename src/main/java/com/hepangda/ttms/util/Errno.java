package com.hepangda.ttms.util;

import java.util.HashMap;

public class Errno {
    private Errno() {}

    private static final HashMap<Integer, String> errnoMap = new HashMap<Integer, String>(){{
        put(100, "Username or password is incorrect.");
        put(101, "Login success.");
        put(102, "Database Server exception.");
        put(103, "Add employee success.");
        put(104, "Fetch employee success.");
        put(105, "Delete employee success.");
        put(106, "Update employee success.");
        put(199, "Permission denied.");
    }};

    public static String getMessage(int errno) {
        return errnoMap.get(errno);
    }
}
