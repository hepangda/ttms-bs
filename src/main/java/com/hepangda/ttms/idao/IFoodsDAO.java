package com.hepangda.ttms.idao;

import com.hepangda.ttms.model.Food;
import com.hepangda.ttms.util.QueryResult;

public interface IFoodsDAO {
    int add(Food fod);

    int delete(Food fod);

    int delete(int id);

    int modify(Food fod);

    QueryResult<Food> query(Food fod);
}
