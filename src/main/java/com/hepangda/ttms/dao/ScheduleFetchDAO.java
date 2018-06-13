package com.hepangda.ttms.dao;

import com.hepangda.ttms.idao.IScheduleFetchDAO;
import com.hepangda.ttms.model.ScheduleFetch;
import com.hepangda.ttms.util.QueryResult;

public class ScheduleFetchDAO extends BaseDAO implements IScheduleFetchDAO {
    @Override
    public QueryResult<ScheduleFetch> query(int id) {
        return normalSelect(new ScheduleFetch(id),111,110);
    }
}
