package com.hepangda.ttms.dao;

/*
CREATE TABLE Studio (
    Stu_ID SERIAL PRIMARY KEY,
    Stu_Name VARCHAR(30) UNIQUE NOT NULL,
    Stu_Type TINYINT NOT NULL,
    Stu_Rows SMALLINT NOT NULL,
    Stu_Cols SMALLINT NOT NULL
);
 */


import com.hepangda.ttms.idao.IStudioDAO;

public class StudioDAO extends BaseDAO implements IStudioDAO {
}
