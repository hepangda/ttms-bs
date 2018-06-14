package com.hepangda.ttms.model.dto;
import com.alibaba.fastjson.annotation.JSONField;
import com.hepangda.ttms.model.Ticket;
import com.hepangda.ttms.util.Utils;

import java.util.ArrayList;

public class SalesTicketResponse {
    @JSONField(name="ok")
    private boolean ok;

    @JSONField(name = "message")
    private String message;

    @JSONField(name = "tkid")
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }

    public int getJ() {
        return j;
    }

    public void setJ(int j) {
        this.j = j;
    }

    @JSONField(name = "i")
    private int i;

    @JSONField(name = "j")
    private int j;

    @JSONField(name = "tickets")
    private ArrayList<Ticket> tickets;

    @JSONField(name = "page")
    private int page;

    @JSONField(name = "pageby")
    private int pageby;

    @JSONField(name = "total")
    private int total;

    @JSONField(name = "results")
    private String[][] results;


    public SalesTicketResponse(boolean ok, String message, String[][] results) {
        this.ok = ok;
        this.message = message;
        this.results = results;
    }

    public SalesTicketResponse(boolean ok, String message, ArrayList<Ticket> tickets) {
        this.ok = ok;
        this.message = message;
        this.tickets = tickets;
    }

    public SalesTicketResponse(boolean ok, String message) {
        this.ok = ok;
        this.message = message;
    }
    public static SalesTicketResponse createAddEditDelete(boolean ok, String messqge){return new SalesTicketResponse(ok,messqge);}

    public static SalesTicketResponse createFetch(boolean ok, String messqge, String[][]arr){
        return new SalesTicketResponse(ok,messqge,arr);
    }

    public String[][] getResults() {
        return results;
    }

    public void setResults(String[][] results) {
        this.results = results;
    }

    public boolean isOk() {
        return ok;
    }

    public void setOk(boolean ok) {
        this.ok = ok;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ArrayList<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(ArrayList<Ticket> tickets) {
        this.tickets = tickets;
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
}