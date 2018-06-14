package com.hepangda.ttms.model.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.hepangda.ttms.model.Ticket;

import java.util.ArrayList;

public class TicketResponse {
    @JSONField(name = "ok")
    private boolean ok;

    @JSONField(name = "message")
    private String messqge;

    @JSONField(name = "tickets")
    private ArrayList<Ticket> tickets;

    @JSONField(name = "results")
    private String[][] results;

    public String[][] getResults() {
        return results;
    }

    public void setResults(String[][] results) {
        this.results = results;
    }

    public TicketResponse(boolean ok, String messqge, ArrayList<Ticket> tickets) {
        this.ok = ok;
        this.messqge = messqge;
        this.tickets = tickets;
    }

    public TicketResponse(boolean ok, String messqge) {
        this.ok = ok;
        this.messqge = messqge;
    }

    public TicketResponse(boolean ok, String messqge, String[][] results) {
        this.ok = ok;
        this.messqge = messqge;
        this.results = results;
    }

    public static TicketResponse createFetch(boolean ok, String messqge, String[][] arr) {
        return new TicketResponse(ok, messqge, arr);
    }

    public static TicketResponse createAddEditDelete(boolean ok, String messqge) {
        return new TicketResponse(ok, messqge);
    }

    public static TicketResponse createFetchx(boolean ok, String messqge, ArrayList<Ticket> tickets) {
        return new TicketResponse(ok, messqge, tickets);
    }

    public boolean isOk() {
        return ok;
    }

    public void setOk(boolean ok) {
        this.ok = ok;
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
}
