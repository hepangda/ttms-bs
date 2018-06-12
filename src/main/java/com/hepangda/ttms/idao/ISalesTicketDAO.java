package com.hepangda.ttms.idao;
import com.hepangda.ttms.model.SalesTicket;
import com.hepangda.ttms.util.QueryResult;
public interface ISalesTicketDAO {
    int add(SalesTicket sale);
    QueryResult<SalesTicket> query(SalesTicket sale);
    int update(SalesTicket sale);
    int delete(SalesTicket sale);
}