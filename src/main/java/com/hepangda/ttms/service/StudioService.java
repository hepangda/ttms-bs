package com.hepangda.ttms.service;

import com.hepangda.ttms.idao.DAOFactory;
import com.hepangda.ttms.idao.IStudioDAO;
import com.hepangda.ttms.model.Studio;
import com.hepangda.ttms.model.dto.StudioRequest;
import com.hepangda.ttms.model.dto.StudioResponse;
import com.hepangda.ttms.util.Errno;
import com.hepangda.ttms.util.QueryResult;
import com.hepangda.ttms.util.Utils;

import javax.servlet.http.HttpSession;


public class StudioService {
    private static int verify(StudioRequest uerq) {
        int err = Utils.validate(uerq);
        if (err != 0)
            return err;
        Studio stu = uerq.getStudio();
        err = Utils.validate(stu);
        return err;
    }

    private static int verify_del(StudioRequest ureq) {
        int err = Utils.validate(ureq);
        if (err != 0)
            return err;
        return err;
    }


    public static StudioResponse add(HttpSession session, StudioRequest ureq) {
        IStudioDAO dao = DAOFactory.createStudioDAO();
        int err = verify(ureq);
        if (err != 0) {
            return StudioResponse.createAddEditDelete(false, Errno.getMessage(err));
        }
        int res = dao.add(ureq.getStudio());
        dao.close();
        return StudioResponse.createAddEditDelete(res == 201, Errno.getMessage(res));
    }

    public static StudioResponse delete(HttpSession session, StudioRequest ureq) {
        IStudioDAO dao = DAOFactory.createStudioDAO();

        int err = verify_del(ureq);
        if (err != 0) {
            return StudioResponse.createAddEditDelete(false, Errno.getMessage(err));
        }

        int res = dao.delete(ureq.getStudio());
        dao.close();
        return StudioResponse.createAddEditDelete(res == 202, Errno.getMessage(res));
    }

    public static StudioResponse edit(HttpSession session, StudioRequest uerq) {
        IStudioDAO dao = DAOFactory.createStudioDAO();

        int err = verify(uerq);
        if (err != 0) {
            return StudioResponse.createAddEditDelete(false, Errno.getMessage(err));
        }

        int res = dao.modify(uerq.getStudio());
        dao.close();
        return StudioResponse.createAddEditDelete(res == 203, Errno.getMessage(res));
    }

    public static StudioResponse fetch(HttpSession session, StudioRequest ureq) {
        IStudioDAO dao = DAOFactory.createStudioDAO();
        QueryResult<Studio> qr = dao.query(ureq.getStudio());
        dao.close();
        return StudioResponse.createFetch(qr.getRetno() == 204, Errno.getMessage(qr.getRetno()), qr.getResults(), ureq);
    }
}
