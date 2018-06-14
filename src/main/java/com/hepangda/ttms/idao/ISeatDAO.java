package com.hepangda.ttms.idao;

import com.hepangda.ttms.model.Seat;
import com.hepangda.ttms.util.QueryResult;

public interface ISeatDAO {
    int add(int Stu_id, int row, int col, int type);

    int add(Seat se);

    int delete(int Stu_id, int row, int col);

    int delete(Seat se);

    int update(Seat se);

    QueryResult<Seat> query(Seat seat);

    void close();
}
