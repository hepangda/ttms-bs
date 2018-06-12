package com.hepangda.ttms.idao;

import com.hepangda.ttms.model.Schedule;
import com.hepangda.ttms.util.QueryResult;

public interface IScheduleDAO {
    int add(Schedule sche);

    int delete(int id);

    int delete(Schedule sche);

    int modify(Schedule sche);

    QueryResult<Schedule> query(Schedule sche);
}
