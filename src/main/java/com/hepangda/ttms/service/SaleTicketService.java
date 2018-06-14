package com.hepangda.ttms.service;

import com.hepangda.ttms.idao.DAOFactory;
import com.hepangda.ttms.idao.ISeatDAO;
import com.hepangda.ttms.idao.ITicketDAO;
import com.hepangda.ttms.model.Seat;
import com.hepangda.ttms.model.Ticket;
import com.hepangda.ttms.model.dto.SalesTicketRequest;
import com.hepangda.ttms.model.dto.SalesTicketResponse;
import com.hepangda.ttms.util.Errno;
import com.hepangda.ttms.util.QueryResult;

import javax.servlet.http.HttpSession;

public class SaleTicketService {
    public static SalesTicketResponse sale(HttpSession session, SalesTicketRequest ureq) {
        ISeatDAO seatdao = DAOFactory.createSeatDAO();
        ITicketDAO ticketdao = DAOFactory.createTicketDAO();
        int row = ureq.getTickets().getRow();
        int column = ureq.getTickets().getCol();
        Seat seat = new Seat();
        seat.setCol(column);
        seat.setRow(row);
        QueryResult<Seat> queryseat = seatdao.query(seat);
        int seatid = queryseat.getResults().get(0).getID();
        Ticket ticket = new Ticket();
        ticket.setSeatid(seatid);
        ticket.setSchid(ureq.getTickets().getSchid());
        QueryResult<Ticket> queryticket = ticketdao.query(ticket);
        ticket = queryticket.getResults().get(0);
        ticket.setStatus(1);
        int res = ticketdao.update(ticket);

        seatdao.close();
        ticketdao.close();
        SalesTicketResponse p = SalesTicketResponse.createAddEditDelete(res==803, Errno.getMessage(res));
        p.setId(ticket.getId());
        return p;
    }

    public static SalesTicketResponse Return(HttpSession session, SalesTicketRequest uerq) {
        ITicketDAO ticketdao = DAOFactory.createTicketDAO();
        Ticket ticket = new Ticket();
        ticket.setId(uerq.getTickets().getId());
        QueryResult<Ticket> queryticket = ticketdao.query(ticket);
        ticket = queryticket.getResults().get(0);
        ticket.setStatus(2);
        int res = ticketdao.update(ticket);
        ticketdao.close();
        return SalesTicketResponse.createAddEditDelete(res==803,Errno.getMessage(805));

    }
}
