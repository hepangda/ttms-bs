package com.hepangda.ttms.util;

import java.util.HashMap;

public class Errno {
    private Errno() {
    }

    private static final HashMap<Integer, String> errnoMap = new HashMap<Integer, String>() {{
        put(100, "Username or password is incorrect.");
        put(101, "Login success.");
        put(102, "Database Server exception.");
        put(103, "Add employee success.");
        put(104, "Fetch employee success.");
        put(105, "Delete employee success.");
        put(106, "Update employee success.");
        put(107, "Employee LoginNameLen Wrong.");
        put(108, "Employee PasswordLen Wrong");
        put(109, "Employee NameLen Wrong");
        put(110, "Employee BornYear Wrong");
        put(111, "Employee PhoneNumber Len Wrong");
        put(112, "Employee Privilege Wrong ");
        put(113, "Add Employee failed.");
        put(114, " EmployeeRequest type wrong.");
        put(199, "Permission denied.");

        put(200, "studio false.");
        put(201, "add studio success.");
        put(202, "delete studio success.");
        put(203, "update studio success.");
        put(204, "select studio success.");
        put(205, "Studio namelen wrong.");
        put(206, "Studio description wrong");
        put(207, "Studio type wrong.");
        put(208, "Studio rows wrong.");
        put(209, "Studio cols wrong.");
        put(210, "Studio operation wrong.");

        put(301,"Add Movie success");
        put(302,"Delete Movie success");
        put(303,"Modify Movie success");
        put(304,"Query Movie success");
        put(305,"Time Length wrong");
        put(306,"Time Range wrong");
        put(307,"Add Movie Failed");
        put(308,"Movie NameLen Wrong");
        put(309,"Movie Type Wrong");
        put(310,"Movie Status Wrong");
        put(311,"Movie Religion Wrong");
        put(312,"Movie Type Wrong");

        put(400," Schedule failed");
        put(401,"Add Schedule success");
        put(402,"Modify Schedule success");
        put(403,"Delete Schedule success");
        put(404,"Query Schedule success");


        put(501, "Add seat success.");
        put(502, "Delete seat success.");
        put(503, "Update seat success.");
        put(504, "Fetch seat success");
        put(599, "Permission denied.");


        put(700, "Food failed");
        put(701, "Add Food success");
        put(702, "Modify Food success");
        put(703, "Query Food Success");
        put(704, "Delete Food Success");
        put(800," Ticket failed");
        put(801,"Add Ticket success");
        put(802,"Delete Ticket success");
        put(803,"Modify Ticket success");
        put(804,"Query Ticket success");
        put(804,"return  Ticket failed");
        put(805,"return  Ticket success");

        put(700, "Food failed");
        put(701, "Add Food success");
        put(702, "Modify Food success");
        put(703, "Query Food Success");
        put(704, "Delete Food Success");


        put(900, "SaleFood failed");
        put(901, "sale Food success");
        put(902, "Query Food success");
    }};

    public static String getMessage(int errno) {
        return errnoMap.get(errno);
    }
}

