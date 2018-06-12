package com.hepangda.ttms.dao;

import com.hepangda.ttms.idao.IStudioDAO;
import com.hepangda.ttms.model.Studio;
import com.hepangda.ttms.util.QueryResult;

public class StudioDAO extends BaseDAO implements IStudioDAO {
    @Override
    public int add(Studio stu) {
        return normalInsert(stu, 200, 201);
    }

    @Override
    public int delete(int id) {
        return delete(new Studio(id));
    }

    public int delete(Studio stu) {
        return normalDelete(stu, 200, 202);
    }

    @Override
    public int modify(Studio stu) {
        return normalUpdate(stu, 200, 203);
    }

    public QueryResult<Studio> query(Studio stu) {
        return normalSelect(stu, 200, 204);
    }
}
