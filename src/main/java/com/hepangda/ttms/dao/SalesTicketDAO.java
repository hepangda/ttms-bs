package com.hepangda.ttms.dao;

import com.hepangda.ttms.idao.ISalesTicketDAO;
import com.hepangda.ttms.model.SalesTicket;
import com.hepangda.ttms.util.QueryResult;

public class SalesTicketDAO extends BaseDAO implements ISalesTicketDAO {

    @Override
    public int add(SalesTicket sale){
        return normalInsert(sale,900,901);
    }

    @Override
    public QueryResult<SalesTicket> query(SalesTicket sale){return normalSelect(sale,900,902);}

    @Override
    public int update(SalesTicket sale){ return normalUpdate(sale,900,903);}

    @Override
    public int delete(SalesTicket sale){
        return normalDelete(sale,900,904);
    }
//    public static void main(String[] args) {
//        SalesTicket test = new SalesTicket();
//        test.setTkid(2);
//        test.setPrice(-20);
//        DAOFactory.createSales_TicketDAO().sale(test);
//        DAOFactory.createSales_TicketDAO().Return(test);
//        //DAOFactory.createTicketDAO().delete(5);
//        test.setId(6);
//        System.out.println(DAOFactory.createTicketDAO().querybyid(test).getResults().get(0).getStatus());
//        test.setId(6);
//        test.setStatus(1);
//        DAOFactory.createTicketDAO().update(test);
//
//    }
}