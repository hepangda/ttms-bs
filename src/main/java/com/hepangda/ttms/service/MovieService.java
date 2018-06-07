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
    public static MovieResponse add(HttpSession session, MovieRequest uerq){
        IMovieDAO dao = DAOFactory.createMovieDAO();
        Movie mov = uerq.getMovies();
        if(mov.getName()==null)
            return MovieResponse.createAddEditDelete(false, Errno.getMessage(300));
        if(mov.getType()<1 &&mov.getType()>16)
            return MovieResponse.createAddEditDelete(false, Errno.getMessage(300));
        if(mov.getReligon()<1&&mov.getReligon()>11)
            return MovieResponse.createAddEditDelete(false, Errno.getMessage(300));
        if(mov.getDescription()==null)
            return MovieResponse.createAddEditDelete(false, Errno.getMessage(300));
        //分割时间
        if(mov.getImage()==null)
            return MovieResponse.createAddEditDelete(false, Errno.getMessage(300));
        int res = dao.add(uerq.getMovies());
        if (res == 301) {
            return MovieResponse.createAddEditDelete(true, Errno.getMessage(res));
        }

        return MovieResponse.createAddEditDelete(false, Errno.getMessage(res));
    }

    public static MovieResponse edit(HttpSession session,MovieRequest uerq){
        IMovieDAO dao = DAOFactory.createMovieDAO();
        int res = dao.modify(uerq.getMovies());
        if (res == 303) {
            return MovieResponse.createAddEditDelete(true, Errno.getMessage(res));
        }

        return MovieResponse.createAddEditDelete(false, Errno.getMessage(res));
    }
    public static MovieResponse fetch(HttpSession session,MovieRequest ureq){
        IMovieDAO dao = DAOFactory.createMovieDAO();
        QueryResult<Movie> qr = dao.query(ureq.getMovies());
        return MovieResponse.createFetch(qr.getRetno()==304, Errno.getMessage(qr.getRetno()),qr.getResults());
    }
    public static MovieResponse delete(HttpSession session,MovieRequest ureq) {
        IMovieDAO dao = DAOFactory.createMovieDAO();
        int res = dao.delete(ureq.getMovies());
        if (res == 302)
            return MovieResponse.createAddEditDelete(true, Errno.getMessage(res));
        return MovieResponse.createAddEditDelete(false,Errno.getMessage(res));
        }
    }

