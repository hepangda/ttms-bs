package com.hepangda.ttms.service;


import com.hepangda.ttms.idao.DAOFactory;
import com.hepangda.ttms.idao.IStudioDAO;
import com.hepangda.ttms.model.Studio;
import com.hepangda.ttms.model.dto.StudioRequest;
import com.hepangda.ttms.model.dto.StudioResponse;
import com.hepangda.ttms.util.Errno;
import com.hepangda.ttms.util.QueryResult;

import javax.servlet.http.HttpSession;

public class StudioService {
    public static StudioResponse add(HttpSession session, StudioRequest ureq) {
        IStudioDAO dao = DAOFactory.createStudioDAO();
        int res = dao.add(ureq.getStudio());
        if (res == 201) {
            return StudioResponse.createAddEditDelete(true, Errno.getMessage(res));
        }
        return StudioResponse.createAddEditDelete(false, Errno.getMessage(res));
    }

    public static StudioResponse delete(HttpSession session, StudioRequest ureq) {
        IStudioDAO dao = DAOFactory.createStudioDAO();
        int res = dao.delete(ureq.getStudio());
        if (res == 202) {
            return StudioResponse.createAddEditDelete(true, Errno.getMessage(res));
        }
        return StudioResponse.createAddEditDelete(false, Errno.getMessage(res));
    }

    public static StudioResponse edit(HttpSession session, StudioRequest uerq) {
        IStudioDAO dao = DAOFactory.createStudioDAO();
        int res = dao.modify(uerq.getStudio());
        if (res == 203) {
            return StudioResponse.createAddEditDelete(true, Errno.getMessage(res));
        }
        return StudioResponse.createAddEditDelete(false, Errno.getMessage(res));
    }

    public static StudioResponse fetch(HttpSession session, StudioRequest ureq) {
        IStudioDAO dao = DAOFactory.createStudioDAO();
        QueryResult<Studio> qr = dao.query(ureq.getStudio());
        return StudioResponse.createFetch(qr.getRetno() == 204, Errno.getMessage(qr.getRetno()), qr.getResults());
    }
}
