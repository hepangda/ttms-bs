package com.hepangda.ttms.dao;

import com.hepangda.ttms.idao.DAOFactory;
import com.hepangda.ttms.idao.IMovieDAO;
import com.hepangda.ttms.model.Movie;
import com.hepangda.ttms.util.QueryResult;

/*表名：Movie
        Mov_ID bigint(20) SERIAL PRIMARY KEY,
        Mov_Name VARCHAR(80) NOT NULL UNIQUE,    影片名
        Mov_Type TINYINT NOT NULL,               影片类型
        Mov_Status TINYINT NOT NULL,             影片状态
        Mov_Religon TINYINT NOT NULL,            影片所属区域
        Mov_Description TEXT,                    影片描述
        Mov_Time TIME NOT NULL,                  影片持续时间
        Mov_ImageURL TEXT                        影片宣传图
   */
public class MovieDAO extends BaseDAO implements IMovieDAO {
    @Override
    public int add(Movie mov) {
        return normalInsert(mov, 300, 301);
    }


    @Override
    public int delete(Movie mov) {
        return normalDelete(mov, 300, 302);
    }

    @Override
    public int delete(int id) {
        return delete(new Movie(id));
    }

    @Override
    public int modify(Movie mov) {
        return normalUpdate(mov,300,303);
    }

    @Override
    public QueryResult<Movie> query(Movie mov) {
        return normalSelect(mov, 300, 304);
    }

//    public static void main(String[] args) {
//        Movie test = new Movie();
//        test.setName("fdsafasd");
//        test.setType(2);;
//        test.setStatus(2);
//        test.setReligon(4);
//        test.setDescription("fawdfasdfdas");
//        test.setTime("12:08:0");
//        test.setImage("fasdfes");
//        DAOFactory.createMovieDAO().add(test);
//    }
}
