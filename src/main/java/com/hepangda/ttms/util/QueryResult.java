package com.hepangda.ttms.util;

import java.util.ArrayList;

public class QueryResult<T> {
    private ArrayList<T> results;
    private int retno;

    public QueryResult(ArrayList<T> results, int retno) {
        this.results = results;
        this.retno = retno;
    }

    public ArrayList<T> getResults() {
        return results;
    }

    public void setResults(ArrayList<T> results) {
        this.results = results;
    }

    public int getRetno() {
        return retno;
    }

    public void setRetno(int retno) {
        this.retno = retno;
    }
}
