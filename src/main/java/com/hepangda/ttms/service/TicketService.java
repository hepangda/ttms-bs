package com.hepangda.ttms.service;

import com.hepangda.ttms.idao.DAOFactory;
import com.hepangda.ttms.idao.ITicketDAO;
import com.hepangda.ttms.idao.ITicketDAO;
import com.hepangda.ttms.model.Ticket;
import com.hepangda.ttms.model.dto.TicketRequest;
import com.hepangda.ttms.model.dto.TicketResponse;
import com.hepangda.ttms.model.dto.TicketResponse;
import com.hepangda.ttms.util.Errno;
import com.hepangda.ttms.util.QueryResult;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
public class TicketService {
    public static TicketResponse add(HttpSession session, TicketRequest uerq) {
        ITicketDAO dao =  DAOFactory.createTicketDAO();
        int res = dao.add(uerq.getTickets());
        if(res == 801) return TicketResponse.createAddEditDelete(true, Errno.getMessage(res));
        return TicketResponse.createAddEditDelete(false,Errno.getMessage(res));
    }
    /*
     put(800," Ticket failed");
            put(801,"Add Ticket success");
            put(802,"Delete Ticket success");
            put(803,"Modify Ticket success");
            put(804,"Query Ticket success");
     */
    public static TicketResponse edit(HttpSession session,TicketRequest uerq){
        ITicketDAO dao = DAOFactory.createTicketDAO();
        int res = dao.update(uerq.getTickets());
        if (res == 803) {
            return TicketResponse.createAddEditDelete(true, Errno.getMessage(res));
        }

        return TicketResponse.createAddEditDelete(false, Errno.getMessage(res));
    }
    public static TicketResponse fetch(HttpSession session,TicketRequest ureq){
        ITicketDAO dao = DAOFactory.createTicketDAO();
        QueryResult<Ticket> qr = dao.query(ureq.getTickets());
        return TicketResponse.createFetch(qr.getRetno()==804, Errno.getMessage(qr.getRetno()),qr.getResults());
    }
    public static TicketResponse delete(HttpSession session,TicketRequest ureq) {
        ITicketDAO dao = DAOFactory.createTicketDAO();
        int res = dao.delete(ureq.getTickets());
        if (res == 802)
            return TicketResponse.createAddEditDelete(true, Errno.getMessage(res));
        return TicketResponse.createAddEditDelete(false,Errno.getMessage(res));
    }
}

