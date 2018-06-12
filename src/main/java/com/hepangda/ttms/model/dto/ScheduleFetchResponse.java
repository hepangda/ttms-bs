package com.hepangda.ttms.model.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.hepangda.ttms.model.ScheduleFetch;
import com.hepangda.ttms.util.Utils;

import java.util.ArrayList;

public class ScheduleFetchResponse {
    /*
     ok: (bool)是否成功,
  message: (string)提示信息,
  page: (int)当前是第几页,
  pageby: (int)一页多少个[暂定永远为20],
  total: (int)总共有多少个符合条件的内容,
   schedule: [
    {
      id: (int)演出计划id,
      time: (string)演出计划时间 格式yyyy-mm-dd hh:mm:ss,
      stuname: (string)演出厅名字,
      movname: (string)影片名字,
      movimage: (string)影片图片url,
      price: (int)场次价格
    },
  ]
}
     */
    @JSONField(name = "ok")
    private boolean ok;

    @JSONField(name = "message")
    private String message;

    @JSONField(name = "page")
    private int page;

    @JSONField(name ="pageby")
    private int pageby;
    @JSONField(name = "total")
    private int total;

    @JSONField(name = "schedule")
    private ArrayList<ScheduleFetch> schedule;

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

    public ArrayList<ScheduleFetch> getSchedule() {
        return schedule;
    }

    public void setSchedule(ArrayList<ScheduleFetch> schedule) {
        this.schedule = schedule;
    }

    public ScheduleFetchResponse(boolean ok, String message, ArrayList<ScheduleFetch> schedule) {
        this.ok = ok;
        this.message = message;
        this.schedule = schedule;
    }

    public static ScheduleFetchResponse createFetch(boolean ok, String message, ArrayList<ScheduleFetch> sfetch, ScheduleFetchRequest req) {
        ScheduleFetchResponse res = new ScheduleFetchResponse(ok,message,
                Utils.slice(sfetch,req.getPage(), req.getPageby()));
        res.setTotal(sfetch.size());
        res.setPage(req.getPage());
        res.setPageby(req.getPageby());
        return res;
    }
}