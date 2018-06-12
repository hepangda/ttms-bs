package com.hepangda.ttms.dao;

import com.hepangda.ttms.idao.IScheduleDAO;
import com.hepangda.ttms.model.Schedule;
import com.hepangda.ttms.util.QueryResult;

public class ScheduleDAO extends BaseDAO implements IScheduleDAO {
    @Override
    public int add(Schedule sche) {
        return normalInsert(sche, 400, 401);
    }

    @Override
    public int delete(Schedule sche) {
        return normalDelete(sche, 400, 403);
    }

    @Override
    public int delete(int id) {
        return delete(new Schedule(id));//new schedule(id)
    }


    @Override
    public int modify(Schedule sche) {
        return normalUpdate(sche, 400, 402);
    }

    @Override
    public QueryResult<Schedule> query(Schedule mov) {
        return normalSelect(mov, 400, 404);
    }
}

