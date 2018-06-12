package com.hepangda.ttms.idao;

import com.hepangda.ttms.model.Studio;
import com.hepangda.ttms.util.QueryResult;

import java.util.ArrayList;

public interface IStudioDAO {
    int add(Studio stu);

    int delete(Studio stu);

    int delete(int id);

    int modify(Studio stu);

    QueryResult<Studio> query(Studio stu);
}
