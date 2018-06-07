package com.hepangda.ttms.service;

import com.hepangda.ttms.annotation.QueryKey;
import com.hepangda.ttms.idao.DAOFactory;
import com.hepangda.ttms.idao.IScheduleDAO;
import com.hepangda.ttms.model.Schedule;
import com.hepangda.ttms.model.dto.ScheduleRequest;
import com.hepangda.ttms.model.dto.ScheduleResponse;
import com.hepangda.ttms.util.Errno;
import com.hepangda.ttms.util.QueryResult;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ScheduleService {
    public static ScheduleResponse add(HttpSession session, ScheduleRequest ureq){
        IScheduleDAO dao = DAOFactory.createScheduleDAO();
        int res = dao.add(ureq.getStudio());
        if(res == 401)
            return ScheduleResponse.createAddEditDelete(true, Errno.getMessage(res));
        return ScheduleResponse.createAddEditDelete(false, Errno.getMessage(res));
    }

    public static ScheduleResponse edit(HttpSession session,ScheduleRequest uerq){
        IScheduleDAO dao = DAOFactory.createScheduleDAO();
        int res = dao.modify(uerq.getStudio());
        if(res == 402)
            return ScheduleResponse.createAddEditDelete(true, Errno.getMessage(res));
        return ScheduleResponse.createAddEditDelete(false, Errno.getMessage(res));
    }
    public static ScheduleResponse delete(HttpSession session,ScheduleRequest uerq){
        IScheduleDAO dao = DAOFactory.createScheduleDAO();
        int res = dao.delete(uerq.getStudio());
        if(res == 403)
            return ScheduleResponse.createAddEditDelete(true, Errno.getMessage(res));
        return ScheduleResponse.createAddEditDelete(false, Errno.getMessage(res));
    }
    public static ScheduleResponse fetch(HttpSession session,ScheduleRequest uerq){
        IScheduleDAO dao = DAOFactory.createScheduleDAO();
        QueryResult<Schedule> qr= dao.query(uerq.getStudio());
        return ScheduleResponse.createFetch(qr.getRetno()==404, Errno.getMessage(qr.getRetno()),qr.getResults());
    }

}
