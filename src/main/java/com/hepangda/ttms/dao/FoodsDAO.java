package com.hepangda.ttms.dao;

import com.hepangda.ttms.idao.DAOFactory;
import com.hepangda.ttms.idao.IFoodsDAO;
import com.hepangda.ttms.idao.IStudioDAO;
import com.hepangda.ttms.model.Food;
import com.hepangda.ttms.model.Studio;
import com.hepangda.ttms.util.QueryResult;

public class FoodsDAO extends BaseDAO implements IFoodsDAO {
    @Override
    public int add(Food fod) {
        return normalInsert(fod, 700, 701);
    }

    @Override
    public int delete(int id) {
        return delete(new Food(id));
    }

    public int delete(Food fod) {
        return normalDelete(fod, 700, 702);
    }

    @Override
    public int modify(Food fod) {
        return normalUpdate(fod, 700, 703);
    }

    public QueryResult<Food> query(Food fod) {
        return normalSelect(fod, 700, 704);
    }
}

//    public static void main(String[] args) {
//        Food food = new Food();
//        food.setId(3);
//        food.setName("lzh");
//        food.setAmount(10);
//        food.setPrice(100);
//        food.setImageurl("dasdasd");
//        DAOFactory.createFoodDAO().query(food);
//    }
//}