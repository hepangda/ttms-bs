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

        put(200, "studio false");
        put(201, "add studio success.");
        put(202, "delete studio success.");
        put(203, "update studio success.");
        put(204, "select studio success.");

        put(300," Movie failed");
        put(301,"Add Movie success");
        put(302,"Delete Movie success");
        put(303,"Modify Movie success");
        put(304,"Query Movie success");

        put(400," Schedule failed");
        put(401,"Add Schedule success");
        put(402,"Modify Schedule success");
        put(403,"Delete Schedule success");
        put(404,"Query Schedule success");


    }};

    public static String getMessage(int errno) {
        return errnoMap.get(errno);
    }
}

