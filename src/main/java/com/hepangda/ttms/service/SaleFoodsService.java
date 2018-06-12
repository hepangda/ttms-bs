package com.hepangda.ttms.service;

import com.hepangda.ttms.idao.DAOFactory;
import com.hepangda.ttms.idao.ISaleFoodsDAO;
import com.hepangda.ttms.model.Food;
import com.hepangda.ttms.model.SaleFoods;
import com.hepangda.ttms.model.dto.SaleFoodsRequest;
import com.hepangda.ttms.model.dto.SaleFoodsResponse;
import com.hepangda.ttms.util.Errno;
import com.hepangda.ttms.util.QueryResult;

import javax.servlet.http.HttpSession;


public class SaleFoodsService {
    public static SaleFoodsResponse sale(HttpSession session, SaleFoodsRequest ureq) {
        ISaleFoodsDAO dao = DAOFactory.createSaleFoodsDAO();
        int res = dao.sale(ureq.getSaleFoods());
        if (res == 901) {
            SaleFoodsService saleFoodsService = new SaleFoodsService();
            saleFoodsService.modify(ureq.getSaleFoods());
            return SaleFoodsResponse.createAddEditDelete(true, Errno.getMessage(res));
        }
        return SaleFoodsResponse.createAddEditDelete(false, Errno.getMessage(res));
    }

    public static SaleFoodsResponse fetch(HttpSession session, SaleFoodsRequest ureq) {
        ISaleFoodsDAO dao = DAOFactory.createSaleFoodsDAO();
        QueryResult<SaleFoods> qr = dao.query(ureq.getSaleFoods());
        return SaleFoodsResponse.createFetch(qr.getRetno() == 902, Errno.getMessage(qr.getRetno()), qr.getResults());
    }

    public int modify(SaleFoods foods) {

        QueryResult<Food> res = DAOFactory.createFoodDAO().query(new Food(foods.getId()));
        int temp = 0;
        for (; temp < res.getResults().size(); temp++) {
            if (res.getResults().get(temp).getId() == foods.getFd_id()) {
                break;
            }
        }
        int id = res.getResults().get(temp).getId();
        String name = res.getResults().get(temp).getName();
        int amount = res.getResults().get(temp).getAmount() - 1;
        int price = res.getResults().get(temp).getPrice();
        String url = res.getResults().get(temp).getImageurl();
        int res1 = DAOFactory.createFoodDAO().modify(new Food(id, name, amount, price, url));
        return 0;
    }
}
