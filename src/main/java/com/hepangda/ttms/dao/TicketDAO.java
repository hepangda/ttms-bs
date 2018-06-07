package com.hepangda.ttms.dao;

import com.hepangda.ttms.annotation.QueryKey;
import com.hepangda.ttms.idao.DAOFactory;
import com.hepangda.ttms.idao.ITicketDAO;
import com.hepangda.ttms.model.Ticket;
import com.hepangda.ttms.util.QueryResult;

public  class TicketDAO extends BaseDAO implements ITicketDAO {


    @Override
    public int add(Ticket Tkt){
        return normalInsert(Tkt,800,801);
    }

    @Override
    public int delete(int id){
        return delete(new Ticket(id));
    }

    @Override
    public int delete(Ticket Tkt){
        return normalDelete(Tkt,800,802);
    }

    @Override
    public int update(Ticket Tkt){
        return normalUpdate(Tkt, 800, 803);
    }

    @Override
    public QueryResult<Ticket> query(Ticket Tkt){
        return normalSelect(Tkt,800, 804);
    }

    /*public static void main(String[] args) {
        Ticket test = new Ticket();
//        test.setSchid(2);
//        test.setSeatid(2);
//        test.setStatus(2);
//        DAOFactory.createTicketDAO().add(test);

        //DAOFactory.createTicketDAO().delete(5);
//        test.setId(6);
//        System.out.println(DAOFactory.createTicketDAO().querybyid(test).getResults().get(0).getStatus());
        test.setId(6);
        test.setStatus(1);
        DAOFactory.createTicketDAO().update(test);

    }*/

}
