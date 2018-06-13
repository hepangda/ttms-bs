package com.hepangda.ttms.service;

import com.hepangda.ttms.annotation.QueryKey;
import com.hepangda.ttms.idao.*;
import com.hepangda.ttms.model.SalesTicket;
import com.hepangda.ttms.model.Schedule;
import com.hepangda.ttms.model.ScheduleFetch;
import com.hepangda.ttms.model.Ticket;
import com.hepangda.ttms.model.dto.ScheduleFetchResponse;
import com.hepangda.ttms.model.dto.ScheduleRequest;
import com.hepangda.ttms.model.dto.ScheduleResponse;
import com.hepangda.ttms.util.Errno;
import com.hepangda.ttms.util.QueryResult;
import com.hepangda.ttms.util.Utils;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;

public class ScheduleService {
    private static int verifyAndTime(ScheduleRequest uerq) {
        int err = Utils.validate(uerq);
        if (err != 0)
            return err;
        Schedule sche = uerq.getSchedule();
        err = Utils.validate(sche, (Object str) -> {
            String[] twopart = ((String) str).split(" ");
            String[] YMD = twopart[0].split("-");
            String[] HMS = twopart[0].split(":");

            if (YMD.length != 3||HMS.length!=3)
                return 406;
            try {
                int y = Integer.parseInt(YMD[0]);
                int month = Integer.parseInt(YMD[1]);
                int d = Integer.parseInt(YMD[2]);
                int h = Integer.parseInt(HMS[0]);
                int minute = Integer.parseInt(YMD[1]);
                int s =Integer.parseInt(YMD[2]);
                if (y<2018||month<1||month>12||d < 1|| d >31
                        || h<0||h>23 ||minute<0||minute >59||s<0||s>59)
                    return 407;
                return 0;
            } catch (NumberFormatException ex) {
                return 408;
            }
        });
        return err;
    }
    private static int verify(ScheduleRequest uerq) {
        int err = Utils.validate(uerq);
        if (err != 0)
            return err;
        err = Utils.validate(uerq.getSchedule());
        if(err!=0)
            return err;
        return err;
    }
    public static ScheduleResponse add(HttpSession session, ScheduleRequest ureq) {
        IScheduleDAO dao = DAOFactory.createScheduleDAO();
        int err = verifyAndTime(ureq);
        if(err!=0)
            return ScheduleResponse.createAddEditDelete(true, Errno.getMessage(err));
        int res = dao.add(ureq.getSchedule());
        if (res == 401)
            return ScheduleResponse.createAddEditDelete(true, Errno.getMessage(res));
        return ScheduleResponse.createAddEditDelete(false, Errno.getMessage(res));
    }

    public static ScheduleResponse edit(HttpSession session, ScheduleRequest ureq) {
        IScheduleDAO dao = DAOFactory.createScheduleDAO();
        int err = verifyAndTime(ureq);
        if(err!=0)
            return ScheduleResponse.createAddEditDelete(true, Errno.getMessage(err));
        int res = dao.modify(ureq.getSchedule());
        if (res == 402)
            return ScheduleResponse.createAddEditDelete(true, Errno.getMessage(res));
        return ScheduleResponse.createAddEditDelete(false, Errno.getMessage(res));
    }

    public static ScheduleResponse delete(HttpSession session, ScheduleRequest ureq) {
        IScheduleDAO schdao = DAOFactory.createScheduleDAO();

        int err = verify(ureq);
        if(err!=0){
            return ScheduleResponse.createAddEditDelete(false, Errno.getMessage(err));
        }
        int deletesche = schdao.delete(ureq.getSchedule());
        if (deletesche == 403)
            return ScheduleResponse.createAddEditDelete(true, Errno.getMessage(deletesche));
        return ScheduleResponse.createAddEditDelete(false, Errno.getMessage(deletesche));
    }

    public static ScheduleFetchResponse fetch(HttpSession session, ScheduleRequest ureq) {
        //获得演出计划的同时,Schedule id ,Scheule time ,拿到Studio的name和Movie的URL,name,prcie;

        IScheduleFetchDAO dao = DAOFactory.createScheduleFetchDAO();

        int err = verify(ureq);
        QueryResult<ScheduleFetch> sfetch = null;
        if(err!=0){
            return ScheduleFetchResponse.createFetch(false, Errno.getMessage(err),sfetch.getResults(),ureq);
        }
        sfetch = dao.query(ureq.getSchedule().getID());
        return ScheduleFetchResponse.createFetch(sfetch.getRetno() == 404, Errno.getMessage(sfetch.getRetno()), sfetch.getResults(),ureq);

    }

}
