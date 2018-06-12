package com.hepangda.ttms.model;

import com.alibaba.fastjson.annotation.JSONField;
import com.hepangda.ttms.annotation.QueryKey;
import com.hepangda.ttms.annotation.QueryTable;

@QueryTable("Seat")
public class Seat {
    @JSONField(name = "id")
    @QueryKey(value = "Set_ID", primaryKey = true, delete = true, insert = false)
    private int id;

    @JSONField(name = "Stu_id")
    @QueryKey(value = "Set_StuID", delete = true)
    private int Stu_id;

    @JSONField(name = "row")
    @QueryKey(value = "Set_Row", delete = true)
    private int row;

    @JSONField(name = "col")
    @QueryKey(value = "Set_Col", delete = true)
    private int col;

    @JSONField(name = "type")
    @QueryKey(value = "Set_Type")
    private int type;

    public Seat(int Stu_id, int row, int col, int type) {
        this.Stu_id = Stu_id;
        this.row = row;
        this.col = col;
        this.type = type;
    }

    public Seat(int Stu_id, int row, int col) {
        this.Stu_id = Stu_id;
        this.col = col;
        this.row = row;
    }

    public Seat() {
    }

    public int getID() {
        return id;
    }

    public void setID(int id) {
        this.id = id;
    }

    public int getStu_id() {
        return Stu_id;
    }

    public void setStu_id(int stu_id) {
        Stu_id = stu_id;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}