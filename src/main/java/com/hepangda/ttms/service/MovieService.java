package com.hepangda.ttms.service;

import com.hepangda.ttms.idao.DAOFactory;
import com.hepangda.ttms.idao.IMovieDAO;
import com.hepangda.ttms.model.Movie;
import com.hepangda.ttms.model.dto.MovieRequest;
import com.hepangda.ttms.model.dto.MovieResponse;
import com.hepangda.ttms.util.Errno;
import com.hepangda.ttms.util.QueryResult;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;

public class MovieService {
    public static MovieResponse add(HttpSession session, MovieRequest ureq) {
        IMovieDAO dao = DAOFactory.createMovieDAO();

        int res = dao.add(ureq.getMovies());
        if (res == 301) {
            return MovieResponse.createAddEditDelete(true, Errno.getMessage(res));
        }

        return MovieResponse.createAddEditDelete(false, Errno.getMessage(res));
    }

    public static MovieResponse edit(HttpSession session, MovieRequest uerq) {
        IMovieDAO dao = DAOFactory.createMovieDAO();
        int res = dao.modify(uerq.getMovies());
        if (res == 303) {
            return MovieResponse.createAddEditDelete(true, Errno.getMessage(res));
        }

        return MovieResponse.createAddEditDelete(false, Errno.getMessage(res));
    }

    public static MovieResponse fetch(HttpSession session, MovieRequest ureq) {
        IMovieDAO dao = DAOFactory.createMovieDAO();
        QueryResult<Movie> qr = dao.query(ureq.getMovies());
        return MovieResponse.createFetch(qr.getRetno() == 304, Errno.getMessage(qr.getRetno()), qr.getResults());
    }

    public static MovieResponse delete(HttpSession session, MovieRequest ureq) {
        IMovieDAO dao = DAOFactory.createMovieDAO();
        int res = dao.delete(ureq.getMovies());
        if (res == 302)
            return MovieResponse.createAddEditDelete(true, Errno.getMessage(res));
        return MovieResponse.createAddEditDelete(false, Errno.getMessage(res));
    }
}

