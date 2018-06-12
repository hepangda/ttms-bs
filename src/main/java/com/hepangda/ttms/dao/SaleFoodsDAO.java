package com.hepangda.ttms.dao;

import com.hepangda.ttms.idao.ISaleFoodsDAO;
import com.hepangda.ttms.model.SaleFoods;
import com.hepangda.ttms.util.QueryResult;

public class SaleFoodsDAO extends BaseDAO implements ISaleFoodsDAO {
    public int sale(SaleFoods foods) {
        return normalInsert(foods , 900 , 901);
    }

    public QueryResult<SaleFoods> query(SaleFoods foods) {
        return normalSelect(foods , 900  , 902);
    }

    public int modify(SaleFoods fod) {
        return normalUpdate(fod, 900, 903);
    }
}
