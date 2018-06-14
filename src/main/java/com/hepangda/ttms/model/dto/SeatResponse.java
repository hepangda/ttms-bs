package com.hepangda.ttms.model.dto;

import com.alibaba.fastjson.annotation.JSONField;

public class SeatResponse {
    @JSONField (name = "ok")
    private boolean ok;

    @JSONField(name = "message")
    private String message;

    @JSONField(name = "results")
    private String[][] results;

    public boolean isOk() {
        return ok;
    }

    public void setOk(boolean ok) {
        this.ok = ok;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String[][] getResults() { return results; }

    public void setResults(String[][] results) { this.results = results; }

    public SeatResponse(boolean ok, String message, String[][] results) {
        this.ok = ok;
        this.message = message;
        this.results = results;
    }

    public SeatResponse(boolean ok, String message) {
        this.ok = ok;
        this.message = message;
    }

    public   static SeatResponse createUpdate(boolean ok , String message){
        return  new SeatResponse(ok,message);
    }

    public static SeatResponse createFetch(boolean ok , String message , String[][] results) {
        return  new SeatResponse(ok,message,results);
    }
}