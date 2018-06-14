package com.hepangda.ttms.model.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.hepangda.ttms.model.Ticket;

public class SalesTicketRequest {
    @JSONField(name = "type")
    private String type;
    @JSONField(name="ticket")
    private Ticket tickets;

    @JSONField(name = "page")
    private int page;

    @JSONField(name = "pageby")
    private int pageby;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Ticket getTickets() {
        return tickets;
    }

    public void setTickets(Ticket tickets) {
        this.tickets = tickets;
    }

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
}