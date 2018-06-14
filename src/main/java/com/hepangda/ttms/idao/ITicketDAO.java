package com.hepangda.ttms.idao;

import com.hepangda.ttms.model.Ticket;
import com.hepangda.ttms.util.QueryResult;

public interface ITicketDAO {
    int add(Ticket Tkt);

    int delete(int id);

    int delete(Ticket Tkt);

    int update(Ticket Tkt);

    QueryResult<Ticket> query(Ticket Tkt);

    void close();
}
