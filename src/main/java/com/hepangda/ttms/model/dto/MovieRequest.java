package com.hepangda.ttms.model.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.hepangda.ttms.annotation.ValidateKey;
import com.hepangda.ttms.model.Movie;

public class MovieRequest {
    @ValidateKey(enums = {"Add", "Edit", "Fetch", "Delete"}, errno = 312)
    @JSONField(name = "type")
    private String type;

    @JSONField(name = "movie")
    private Movie movies;

    @JSONField(name = "page")
    private int page;

    @JSONField(name = "pageby")
    private int pageby;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Movie getMovies() {
        return movies;
    }

    public void setMovies(Movie movies) {
        this.movies = movies;
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
}
