package com.hepangda.ttms.model.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.hepangda.ttms.model.Employee;

import java.util.ArrayList;

public class EmployeeResponse {
    @JSONField(name = "ok")
    private boolean ok;

    @JSONField(name = "message")
    private String message;

    @JSONField(name = "employees")
    private ArrayList<Employee> employees;

    private EmployeeResponse(boolean ok, String message) {
        this.ok = ok;
        this.message = message;
    }

    private EmployeeResponse(boolean ok, String message, ArrayList<Employee> employees) {
        this.ok = ok;
        this.message = message;
        this.employees = employees;
    }

    public static EmployeeResponse createAddFetch(boolean ok, String message) {
        return new EmployeeResponse(ok, message);
    }

    public static EmployeeResponse createGet(boolean ok, String message, ArrayList<Employee> employees) {
        return new EmployeeResponse(ok, message, employees);
    }

    public static EmployeeResponse createGet(boolean ok, String message, Employee employee) {
        return new EmployeeResponse(ok, message, new ArrayList<Employee>() {{
            add(employee);
        }});
    }
}
