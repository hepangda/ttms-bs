package com.hepangda.ttms.model.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.hepangda.ttms.model.Employee;
import com.hepangda.ttms.util.Utils;

import java.util.ArrayList;

public class EmployeeResponse {
    @JSONField(name = "ok")
    private boolean ok;

    @JSONField(name = "message")
    private String message;

    @JSONField(name = "employees")
    private ArrayList<Employee> employees;

    @JSONField(name = "page")
    private int page;

    @JSONField(name = "pageby")
    private int pageby;

    @JSONField(name = "total")
    private int total;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPageby() {
        return pageby;
    }

    public void setPageby(int pageby) {
        this.pageby = pageby;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

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

    public static EmployeeResponse createGet(boolean ok, String message, ArrayList<Employee> employees, EmployeeRequest req) {
        EmployeeResponse res = new EmployeeResponse(ok, message,
                Utils.slice(employees, req.getPage(), req.getPageby()));
        res.setTotal(employees.size());
        res.setPage(req.getPage());
        res.setPageby(req.getPageby());
        return res;
    }

    public static EmployeeResponse createGet(boolean ok, String message, Employee employee, EmployeeRequest req) {
        return createGet(ok, message, new ArrayList<Employee>() {{
            add(employee);
        }}, req);
    }

    public boolean isOk() {
        return ok;
    }

    public void setOk(boolean ok) {
        this.ok = ok;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ArrayList<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(ArrayList<Employee> employees) {
        this.employees = employees;
    }
}
