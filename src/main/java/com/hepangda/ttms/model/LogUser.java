package com.hepangda.ttms.model;

import com.hepangda.ttms.exception.NonLoginException;

public class LogUser {
    private LogUser() {}

    private static User _inst = null;
    public static User getInstance() throws NonLoginException {
        if (_inst == null) {
            _inst = new User();
        }
        return _inst;
    }

    public static boolean checkPrivilege() {
        // TODO: add code here
        return false;
    }

    public static boolean tryLoginIn(LoginInfo info) {
        // TODO: if login, returns true, otherwise false

        return false;
    }
}
