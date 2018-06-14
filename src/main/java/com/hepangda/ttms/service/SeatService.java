package com.hepangda.ttms.service;

import com.hepangda.ttms.idao.DAOFactory;
import com.hepangda.ttms.idao.ISeatDAO;
import com.hepangda.ttms.idao.IStudioDAO;
import com.hepangda.ttms.model.Seat;
import com.hepangda.ttms.model.Studio;
import com.hepangda.ttms.model.dto.SeatRequest;
import com.hepangda.ttms.model.dto.SeatResponse;
import com.hepangda.ttms.util.Errno;
import com.hepangda.ttms.util.QueryResult;

import javax.servlet.http.HttpSession;

public class SeatService {
    public static SeatResponse Update(HttpSession session, SeatRequest ureq) {
        ISeatDAO dao = DAOFactory.createSeatDAO();
        int res = dao.update(ureq.getSeat());
        dao.close();
        return SeatResponse.createUpdate(res != 599, Errno.getMessage(res));
    }

    public static SeatResponse fetch(HttpSession session, SeatRequest ureq) {
        ISeatDAO dao = DAOFactory.createSeatDAO();
        QueryResult<Seat> qr = dao.query(ureq.getSeat());
        SeatService seatService = new SeatService();
        dao.close();
        return SeatResponse.createFetch(qr.getRetno() == 504, Errno.getMessage(qr.getRetno()), seatService.modify(ureq.getSeat()));
    }

    public String[][] modify(Seat seat) {
        ISeatDAO stdao = DAOFactory.createSeatDAO();
        IStudioDAO sudao = DAOFactory.createStudioDAO();
        QueryResult<Studio> res = sudao.query(new Studio(seat.getStu_id()));
        QueryResult<Seat> req = stdao.query(seat);

        int temp = 0;
        int temp1 = 0;
        for (; temp < res.getResults().size(); temp++) {
            if (res.getResults().get(temp).getId() == seat.getStu_id()) {
                break;
            }
        }
        int row = res.getResults().get(temp).getRow();
        int col = res.getResults().get(temp).getColumn();
        String[][] zhanglei = new String[row][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                for (; temp1 < req.getResults().size(); temp1++) {
                    if (req.getResults().get(temp1).getRow() == i + 1 && req.getResults().get(temp1).getCol() == j + 1) {
                        if (req.getResults().get(temp1).getType() == 1) {
                            zhanglei[i][j] = "broken";
                            temp1 = 0;
                            break;
                        } else if (req.getResults().get(temp1).getType() == 2) {
                            zhanglei[i][j] = "normal";
                            temp1 = 0;
                            break;
                        }
                        if (req.getResults().get(temp1).getType() == 3) {
                            zhanglei[i][j] = "comfort";
                            temp1 = 0;
                            break;
                        }
                        if (req.getResults().get(temp1).getType() == 4) {
                            zhanglei[i][j] = "vip";
                            temp1 = 0;
                            break;
                        }

                    }
                }
            }
        }
        stdao.close();
        sudao.close();
        return zhanglei;
    }
}