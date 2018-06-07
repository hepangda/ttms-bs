package com.hepangda.ttms.idao;

import com.hepangda.ttms.model.Seat;
import com.hepangda.ttms.util.QueryResult;

/*

Seat
    Seat_ID SERIAL PRIMARY KEY,
    Seat_StuID BIGINT UNSIGNED NOT NULL FK Studio(Stu_ID),  放映厅ID
    Seat_Row INT NOT NULL,            行号
    Seat_Col INT NOT NULL,            列号
    Seat_Type TINYINT NOT NULL,       座位类型
 */
public interface ISeatDAO {

    int add( int Stu_id,int row,int col,int type);
    int add(Seat se);

    int delete (int Stu_id,int row,int col);
    int delete (Seat se);

    int update(Seat se);


    QueryResult <Seat> query(Seat seat);

}
