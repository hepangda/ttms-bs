package com.hepangda.ttms.service;

import com.hepangda.ttms.idao.DAOFactory;
import com.hepangda.ttms.idao.ISeatDAO;
import com.hepangda.ttms.model.dto.SeatRequest;
import com.hepangda.ttms.model.dto.SeatResponse;
import com.hepangda.ttms.util.Errno;

import javax.servlet.http.HttpSession;

public class SeatService {
    public static SeatResponse Update(HttpSession session, SeatRequest ureq) {
        ISeatDAO dao = DAOFactory.createSeatDAO();
        int res = dao.update(ureq.getSeat());
        if (res == 599) {
            return SeatResponse.createUpdate(false, Errno.getMessage(res));
        }
        return SeatResponse.createUpdate(true, Errno.getMessage(res));
    }
}