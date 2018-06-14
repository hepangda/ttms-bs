package com.hepangda.ttms.idao;

import com.hepangda.ttms.model.Movie;
import com.hepangda.ttms.util.QueryResult;

public interface IMovieDAO {
    int add(Movie mov);

    int delete(int id);

    int delete(Movie mov);

    int modify(Movie mov);

    QueryResult<Movie> query(Movie mov);

    void close();
}
