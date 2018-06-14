package com.hepangda.ttms.service;

import com.hepangda.ttms.idao.*;
import com.hepangda.ttms.idao.ITicketDAO;
import com.hepangda.ttms.model.Schedule;
import com.hepangda.ttms.model.Seat;
import com.hepangda.ttms.model.Studio;
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
        ITicketDAO dao = DAOFactory.createTicketDAO();
        int res = dao.add(uerq.getTickets());
        dao.close();
        return TicketResponse.createAddEditDelete(res == 801, Errno.getMessage(res));
    }

    public static TicketResponse edit(HttpSession session, TicketRequest uerq) {
        ITicketDAO dao = DAOFactory.createTicketDAO();
        int res = dao.update(uerq.getTickets());
        dao.close();
        return TicketResponse.createAddEditDelete(res == 803, Errno.getMessage(res));
    }

    public static TicketResponse fetchx(HttpSession session, TicketRequest ureq) {
        ITicketDAO dao = DAOFactory.createTicketDAO();
        QueryResult<Ticket> qr = dao.query(ureq.getTickets());
        dao.close();
        return TicketResponse.createFetchx(qr.getRetno() == 804, Errno.getMessage(qr.getRetno()), qr.getResults());
    }

    public static TicketResponse fetch(HttpSession session, TicketRequest ureq){
        ITicketDAO ticketdao = DAOFactory.createTicketDAO();
        IScheduleDAO schdao = DAOFactory.createScheduleDAO();
        IStudioDAO studao = DAOFactory.createStudioDAO();
        ISeatDAO seatdao = DAOFactory.createSeatDAO();
        int row=0,column=0;

        QueryResult<Schedule> quersche = schdao.query(new Schedule(ureq.getTickets().getSchid()));
        int stuid = quersche.getResults().get(0).getMovID();
        QueryResult<Studio> stu = studao.query(new Studio(stuid));
        row = stu.getResults().get(0).getRow();
        column = stu.getResults().get(0).getColumn();
        String[][] arr = new String[row][column];
        Ticket temp = new Ticket();
        temp.setSchid(ureq.getTickets().getSchid());
        QueryResult<Ticket> queryticket = ticketdao.query(temp);
        if(queryticket.getRetno()!=804){
            return TicketResponse.createFetch(false, Errno.getMessage(queryticket.getRetno()),arr);
        }

        int tickenum = queryticket.getResults().size();

       ArrayList<Ticket> status = queryticket.getResults();
        for(int i = 0;i < tickenum;i++){
            if(status.get(i).getStatus()==1){
                QueryResult<Seat> queryseat = seatdao.query(new Seat(status.get(i).getSeatid()));
                int seatrow = queryseat.getResults().get(0).getRow()-1;
                int seatcolumn = queryseat.getResults().get(0).getCol()-1;
                arr[seatrow][seatcolumn] = "Sold";
            }else if(status.get(i).getStatus()==2){
                QueryResult<Seat> queryseat = seatdao.query(new Seat(status.get(i).getSeatid()));
                int seatrow = queryseat.getResults().get(0).getRow()-1;
                int seatcolumn = queryseat.getResults().get(0).getCol()-1;
                arr[seatrow][seatcolumn] = "Unsold";

            }else if(status.get(i).getStatus()==3){
                QueryResult<Seat> queryseat = seatdao.query(new Seat(status.get(i).getSeatid()));
                int seatrow = queryseat.getResults().get(0).getRow()-1;
                int seatcolumn = queryseat.getResults().get(0).getCol()-1;
                arr[seatrow][seatcolumn] = "Broken";
            }
        }

        return TicketResponse.createFetch(true, Errno.getMessage(804), arr);
    }

    public static TicketResponse delete(HttpSession session, TicketRequest ureq) {
        ITicketDAO dao = DAOFactory.createTicketDAO();
        int res = dao.delete(ureq.getTickets());
        dao.close();
        return TicketResponse.createAddEditDelete(res == 802, Errno.getMessage(res));
    }
}

