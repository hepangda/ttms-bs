package com.hepangda.ttms.model;
import com.alibaba.fastjson.annotation.JSONField;
import com.hepangda.ttms.annotation.QueryKey;
import com.hepangda.ttms.annotation.QueryTable;

import java.util.*;
@QueryTable("Studio")
public class Studio {

    @JSONField(name = "id")
    @QueryKey(value = "Stu_ID", insert = false, delete = true, primaryKey = true)
    private int id;

    @JSONField(name = "name")
    @QueryKey(value = "Stu_Name")
    private  String name;

    @JSONField(name = "description")
    @QueryKey(value = "Stu_Description")
    private String description;

    @JSONField(name = "type")
    @QueryKey(value = "Stu_Type")
    private int type;

    @JSONField(name = "rows")
    @QueryKey(value = "Stu_Rows")
    private int row;

    @JSONField(name = "cols")
    @QueryKey(value = "Stu_cols")
    private int column;

    public Studio() {
    }
    public Studio(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }
}
