package com.hepangda.ttms.dao;

import com.hepangda.ttms.idao.IScheduleFetchDAO;
import com.hepangda.ttms.model.ScheduleFetch;
import com.hepangda.ttms.util.QueryResult;

public class ScheduleFetchDAO extends BaseDAO implements IScheduleFetchDAO {
    @Override
    public QueryResult<ScheduleFetch> query(ScheduleFetch sf) {
        return normalSelect(sf,111,110);
    }
}