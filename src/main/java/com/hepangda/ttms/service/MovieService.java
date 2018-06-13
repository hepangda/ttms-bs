package com.hepangda.ttms.service;

import com.hepangda.ttms.annotation.ValidateKey;
import com.hepangda.ttms.idao.DAOFactory;
import com.hepangda.ttms.idao.IMovieDAO;
import com.hepangda.ttms.model.Movie;
import com.hepangda.ttms.model.dto.MovieRequest;
import com.hepangda.ttms.model.dto.MovieResponse;
import com.hepangda.ttms.util.Errno;
import com.hepangda.ttms.util.QueryResult;
import com.hepangda.ttms.util.Utils;

import javax.servlet.http.HttpSession;
import java.sql.Statement;
import java.util.ArrayList;

/*
@JSONField(name = "type")
    private String type;

    @JSONField(name = "movie")
    private Movie movies;

    @JSONField(name ="page")
    private int page;

    @JSONField(name="pageby")
    private int pageby;
 */
public class MovieService {
    private static int verifyAndTime(MovieRequest uerq) {
        int err = Utils.validate(uerq);
        if (err != 0)
            return err;
        Movie mov = uerq.getMovies();
        err = Utils.validate(mov, (Object str) -> {
            String[] slice = ((String) str).split(":");
            if (slice.length != 3)
                return 305;
            try {
                int h = Integer.parseInt(slice[0]);
                int m = Integer.parseInt(slice[1]);
                int s = Integer.parseInt(slice[2]);
                if (h < 0 || h > 23 || m < 0 || m > 59 || s < 0 || s > 59)
                    return 306;
                return 0;
            } catch (NumberFormatException ex) {
                return 306;
            }
        });
        return err;
    }

    private static int verify(MovieRequest uerq) {
        int err = Utils.validate(uerq);
        if (err != 0)
            return err;
        Movie mov = uerq.getMovies();
        err = Utils.validate(mov);
        return err;
    }
    public static MovieResponse add(HttpSession session, MovieRequest uerq) {
        IMovieDAO dao = DAOFactory.createMovieDAO();
        int err = verifyAndTime(uerq);
        if (err != 0) {
            return MovieResponse.createAddEditDelete(false, Errno.getMessage(err));
        }
        if (dao.add(uerq.getMovies()) != 301)
            return MovieResponse.createAddEditDelete(false, Errno.getMessage(307));
        return MovieResponse.createAddEditDelete(true, Errno.getMessage(301));
    }

    public static MovieResponse edit(HttpSession session, MovieRequest uerq) {
        IMovieDAO dao = DAOFactory.createMovieDAO();
        int err = verifyAndTime(uerq);
        if (err != 0) {
            return MovieResponse.createAddEditDelete(false, Errno.getMessage(err));
        }
        int res = dao.modify(uerq.getMovies());
        if (res == 303) {
            return MovieResponse.createAddEditDelete(true, Errno.getMessage(res));
        }

        return MovieResponse.createAddEditDelete(false, Errno.getMessage(315));
    }

    public static MovieResponse fetch(HttpSession session, MovieRequest ureq) {
        IMovieDAO dao = DAOFactory.createMovieDAO();
        int err = verify(ureq);
        QueryResult<Movie> mo = null;
        if (err != 0) {
            return MovieResponse.createFetch(false, Errno.getMessage(err),
                    mo.getResults(), ureq);
        }
        QueryResult<Movie> qr = dao.query(ureq.getMovies());
        Movie temp = qr.getResults().get(0);
        return MovieResponse.createFetch(qr.getRetno() == 304, Errno.getMessage(qr.getRetno()), qr.getResults(), ureq);
    }

    public static MovieResponse delete(HttpSession session, MovieRequest ureq) {
        IMovieDAO dao = DAOFactory.createMovieDAO();

        int err = verify(ureq);
        if (err != 0) {
            return MovieResponse.createAddEditDelete(false, Errno.getMessage(err));
        }
        int res = dao.delete(ureq.getMovies());
        if (res == 302)
            return MovieResponse.createAddEditDelete(true, Errno.getMessage(res));
        return MovieResponse.createAddEditDelete(false, Errno.getMessage(314));
    }
}

