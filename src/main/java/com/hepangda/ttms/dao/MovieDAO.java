package com.hepangda.ttms.dao;

import com.hepangda.ttms.idao.IMovieDAO;
import com.hepangda.ttms.model.Movie;
import com.hepangda.ttms.util.QueryResult;

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
}
