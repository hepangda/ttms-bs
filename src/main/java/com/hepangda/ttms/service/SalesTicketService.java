package com.hepangda.ttms.service;

import com.hepangda.ttms.idao.DAOFactory;
import com.hepangda.ttms.idao.ISalesTicketDAO;
import com.hepangda.ttms.idao.IScheduleDAO;
import com.hepangda.ttms.model.SalesTicket;
import com.hepangda.ttms.model.Schedule;
import com.hepangda.ttms.model.Ticket;
import com.hepangda.ttms.model.dto.SalesTicketRequest;
import com.hepangda.ttms.model.dto.SalesTicketResponse;
import com.hepangda.ttms.idao.ITicketDAO;
import com.hepangda.ttms.util.Errno;
import com.hepangda.ttms.util.QueryResult;

import javax.servlet.http.HttpSession;

public class SalesTicketService {
    public static SalesTicketResponse sale(HttpSession session, SalesTicketRequest uerq) {
        ISalesTicketDAO Sdao =  DAOFactory.createSalesTicketDAO();
        ITicketDAO Tdao =  DAOFactory.createTicketDAO();
        IScheduleDAO Schdao =  DAOFactory.createScheduleDAO();
        SalesTicket sale = new SalesTicket();
        sale.setTkid(uerq.getTickets().getId());
        sale.setPrice((Schdao.query(new Schedule(uerq.getTickets().getSchid()))).getResults().get(0).getPrice());
        uerq.getTickets().setStatus(uerq.getTickets().getStatus()+1);

        if(Sdao.add(sale) == 901 && Tdao.update(uerq.getTickets())==803)
            return SalesTicketResponse.createAddEditDelete(true, Errno.getMessage(902));
        return SalesTicketResponse.createAddEditDelete(false,Errno.getMessage(901));
    }

    public static SalesTicketResponse Return(HttpSession session, SalesTicketRequest uerq) {
        ISalesTicketDAO Sdao =  DAOFactory.createSalesTicketDAO();
        ITicketDAO Tdao =  DAOFactory.createTicketDAO();
        IScheduleDAO Schdao =  DAOFactory.createScheduleDAO();
        SalesTicket sale = new SalesTicket();
        sale.setTkid(uerq.getTickets().getId());
        sale.setPrice(-(Schdao.query(new Schedule(uerq.getTickets().getSchid()))).getResults().get(0).getPrice());
        uerq.getTickets().setStatus(uerq.getTickets().getStatus()-1);
        Tdao.update(uerq.getTickets());
        //int res = Sdao.add(sale);
        //if(res == 901)
        if(Sdao.add(sale) == 901 && Tdao.update(uerq.getTickets())==803)
            return SalesTicketResponse.createAddEditDelete(true, Errno.getMessage(902));
        return SalesTicketResponse.createAddEditDelete(false,Errno.getMessage(901));
    }

    public static SalesTicketResponse fetch(HttpSession session, SalesTicketRequest ureq){
        ITicketDAO dao = DAOFactory.createTicketDAO();
        QueryResult<Ticket> qr = dao.query(ureq.getTickets());
        return SalesTicketResponse.createFetch(qr.getRetno()==804, Errno.getMessage(qr.getRetno()),qr.getResults(),ureq);
    }
}