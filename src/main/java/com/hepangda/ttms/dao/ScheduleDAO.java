package com.hepangda.ttms.dao;
/*
CREATE TABLE Schedule (
    Sch_ID SERIAL PRIMARY KEY,
    Sch_MoiveID BIGINT UNSIGNED NOT NULL FORIGEN KEY REFERENCES Movie(Mov_ID),
    Sch_StudioID BIGINT UNSIGNED NOT NULL FORIGEN KEY REFERENCES Studio(Stu_ID),
    Sch_Price MONEY NOT NULL
 */
public class ScheduleDAO extends BaseDAO{
}
