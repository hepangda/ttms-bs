package com.hepangda.ttms.idao;

import com.hepangda.ttms.model.SaleFoods;
import com.hepangda.ttms.util.QueryResult;

public interface ISaleFoodsDAO {
    int sale(SaleFoods foods);
    QueryResult<SaleFoods> query(SaleFoods foods);
    int  modify(SaleFoods fod);
}
