package com.hepangda.ttms.model.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.hepangda.ttms.model.Movie;
import com.hepangda.ttms.model.Schedule;

import java.util.ArrayList;

public class MovieResponse {
    @JSONField(name="ok")
    private boolean ok;

    @JSONField(name = "message")
    private String messqge;

    @JSONField(name = "movies")
    private ArrayList<Movie> movies;

    public MovieResponse(boolean ok, String messqge) {
        this.ok = ok;
        this.messqge = messqge;
    }

    public MovieResponse(boolean ok, String messqge, ArrayList<Movie> movies) {
        this.ok = ok;
        this.messqge = messqge;
        this.movies = movies;
    }
    public static MovieResponse createAddEditDelete(boolean ok,String messqge){
        return new MovieResponse(ok,messqge);
    }
    public static MovieResponse createFetch(boolean ok, String message,ArrayList<Movie> movies) {
        return new MovieResponse(ok, message, movies);
    }

    public boolean isOk() {
        return ok;
    }

    public void setOk(boolean ok) {
        this.ok = ok;
    }

    public String getMessqge() {
        return messqge;
    }

    public void setMessqge(String messqge) {
        this.messqge = messqge;
    }

    public ArrayList<Movie> getMovies() {
        return movies;
    }

    public void setMovies(ArrayList<Movie> movies) {
        this.movies = movies;
    }
}
