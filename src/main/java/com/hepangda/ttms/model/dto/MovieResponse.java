package com.hepangda.ttms.model.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.hepangda.ttms.model.Movie;
import com.hepangda.ttms.util.Utils;

import java.util.ArrayList;

public class MovieResponse {
    @JSONField(name = "ok")
    private boolean ok;

    @JSONField(name = "message")
    private String message;

    @JSONField(name = "page")
    private int page;

    @JSONField(name = "pageby")
    private int pageby;

    @JSONField(name = "total")
    private int total;


    @JSONField(name = "movies")
    private ArrayList<Movie> movies;

    public MovieResponse(boolean ok, String message) {
        this.ok = ok;
        this.message = message;
    }

    public MovieResponse(boolean ok, String message, ArrayList<Movie> movies) {
        this.ok = ok;
        this.message = message;
        this.movies = movies;
    }


    public static MovieResponse createAddEditDelete(boolean ok, String messqge) {
        return new MovieResponse(ok, messqge);
    }

    public static MovieResponse createFetch(boolean ok, String message, ArrayList<Movie> movies, MovieRequest req) {
        MovieResponse res = new MovieResponse(ok, message,
                Utils.slice(movies, req.getPage(), req.getPageby()));
        res.setTotal(movies.size());
        res.setPage(req.getPage());
        res.setPageby(req.getPageby());
        return res;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPageby() {
        return pageby;
    }

    public void setPageby(int pageby) {
        this.pageby = pageby;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public boolean isOk() {
        return ok;
    }

    public void setOk(boolean ok) {
        this.ok = ok;
    }

    public String getMessage() {
        return message;
    }

    public void setMessqge(String message) {
        this.message = message;
    }

    public ArrayList<Movie> getMovies() {
        return movies;
    }

    public void setMovies(ArrayList<Movie> movies) {
        this.movies = movies;
    }
}
