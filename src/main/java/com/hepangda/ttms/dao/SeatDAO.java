package com.hepangda.ttms.dao;

import com.hepangda.ttms.idao.ISeatDAO;
import com.hepangda.ttms.model.Seat;
import com.hepangda.ttms.util.QueryResult;

public class SeatDAO extends BaseDAO implements ISeatDAO {
    @Override
    public int add(int Stu_id, int row, int col, int type) {
        return add(new Seat(Stu_id, row, col, type));
    }

    @Override
    public int add(Seat se) {
        return normalInsert(se, 599, 501);
    }

    @Override
    public int delete(int Stu_id, int row, int col) {
        return delete(new Seat(Stu_id, row, col));
    }

    public int delete(Seat se) {
        return normalDelete(se, 599, 502);
    }

    @Override
    public int update(Seat se) {       //修改
        return normalUpdate(se, 599, 503);
    }

    @Override
    public QueryResult<Seat> query(Seat seat) {                  //查询
        return normalSelect(seat, 599, 504);
    }
}