package com.hepangda.ttms.model.dto;
import com.alibaba.fastjson.annotation.JSONField;
import com.hepangda.ttms.model.Ticket;
import com.hepangda.ttms.util.Utils;

import java.util.ArrayList;

public class SalesTicketResponse {
    @JSONField(name="ok")
    private boolean ok;

    @JSONField(name = "message")
    private String messqge;

    @JSONField(name = "tickets")
    private ArrayList<Ticket> tickets;

    @JSONField(name = "page")
    private int page;

    @JSONField(name = "pageby")
    private int pageby;

    @JSONField(name = "total")
    private int total;

    public SalesTicketResponse(boolean ok, String messqge, ArrayList<Ticket> tickets) {
        this.ok = ok;
        this.messqge = messqge;
        this.tickets = tickets;
    }

    public SalesTicketResponse(boolean ok, String messqge) {
        this.ok = ok;
        this.messqge = messqge;
    }
    public static SalesTicketResponse createAddEditDelete(boolean ok, String messqge){return new SalesTicketResponse(ok,messqge);}

    public static SalesTicketResponse createFetch(boolean ok, String messqge, ArrayList<Ticket> tickets,SalesTicketRequest req){
        SalesTicketResponse res = new SalesTicketResponse(ok,messqge,
                Utils.slice(tickets,req.getPage(),req.getPageby()));
        return res;
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

    public String getMessqge() {
        return messqge;
    }

    public void setMessqge(String messqge) {
        this.messqge = messqge;
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