package com.hepangda.ttms.service;

import com.hepangda.ttms.idao.DAOFactory;
import com.hepangda.ttms.idao.IFoodsDAO;
import com.hepangda.ttms.idao.IStudioDAO;
import com.hepangda.ttms.model.Food;
import com.hepangda.ttms.model.Studio;
import com.hepangda.ttms.model.dto.FoodRequest;
import com.hepangda.ttms.model.dto.FoodResponse;
import com.hepangda.ttms.model.dto.StudioRequest;
import com.hepangda.ttms.model.dto.StudioResponse;
import com.hepangda.ttms.util.Errno;
import com.hepangda.ttms.util.QueryResult;

import javax.servlet.http.HttpSession;

public class FoodService {
    public static FoodResponse add(HttpSession session , FoodRequest ureq) {
        IFoodsDAO dao = DAOFactory.createFoodDAO();
        int res = dao.add(ureq.getFood());
        if(res == 701) {
            return  FoodResponse.createAddEditDelete(true , Errno.getMessage(res));
        }
        return FoodResponse.createAddEditDelete(false , Errno.getMessage(res));
    }

    public static FoodResponse delete(HttpSession session ,FoodRequest ureq) {
        IFoodsDAO dao = DAOFactory.createFoodDAO();
        int res = dao.delete(ureq.getFood());
        if(res == 702) {
            return FoodResponse.createAddEditDelete(true , Errno.getMessage(res));
        }
        return FoodResponse.createAddEditDelete(false , Errno.getMessage(res));
    }

    public static FoodResponse edit(HttpSession session , FoodRequest ureq) {
        IFoodsDAO dao = DAOFactory.createFoodDAO();
        int res = dao.modify(ureq.getFood());
        if(res == 703) {
            return FoodResponse.createAddEditDelete(true , Errno.getMessage(res));
        }
        return FoodResponse.createAddEditDelete(false , Errno.getMessage(res));
    }

    public static FoodResponse fetch(HttpSession session ,FoodRequest ureq) {
        IFoodsDAO dao = DAOFactory.createFoodDAO();
        QueryResult<Food> qr = dao.query(ureq.getFood());
        return FoodResponse.createFetch(qr.getRetno() == 704 , Errno.getMessage(qr.getRetno()) , qr.getResults());
    }
}
