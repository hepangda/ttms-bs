package com.hepangda.ttms.dao;

import com.hepangda.ttms.idao.IStudioDAO;
import com.hepangda.ttms.model.Studio;
import com.hepangda.ttms.util.QueryResult;

public class StudioDAO extends BaseDAO implements IStudioDAO {
    @Override
    public int add(Studio stu) {
        return normalInsert(stu , 200 , 201);
    }

    @Override
    public int delete(int id) {
        return delete(new Studio(id));
    }

    public int delete(Studio stu) {
        return normalDelete(stu , 200 , 202);
    }

    @Override
    public int modify(Studio stu) {
        return normalUpdate(stu , 200 , 203);
    }

    public QueryResult<Studio> query(Studio stu) {
        return normalSelect(stu , 200  , 204);
    }

//    public static void main(String[] args) {
//        Studio studio = new Studio();
//        studio.setName("zahnglei");
//        studio.setDescription("zhenshuai");
//        studio.setType(1);
//        studio.setColumn(10);
//        studio.setRow(10);
//        DAOFactory.createStudioDAO().add(studio);
//    }

//    public static void main(String[] args) {
//        Studio studio = new Studio();
//
//
////        DAOFactory.createStudioDAO().delete(1);
//        DAOFactory.createStudioDAO().delete(10);
//    }
}
