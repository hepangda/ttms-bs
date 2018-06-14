
package com.hepangda.ttms.model;

import com.alibaba.fastjson.annotation.JSONField;
import com.hepangda.ttms.annotation.QueryKey;
import com.hepangda.ttms.annotation.QueryTable;
import com.hepangda.ttms.annotation.ValidateKey;

import java.util.*;

@QueryTable("Studio")
public class Studio {
    @ValidateKey(maxRange = 0,errno = 211)
    @JSONField(name = "id")
    @QueryKey(value = "Stu_ID", insert = false, delete = true, primaryKey = true)
    private int id;

    @ValidateKey(maxLen = 30, errno = 205)
    @JSONField(name = "name")
    @QueryKey(value = "Stu_Name")
    private String name;

    @ValidateKey(maxLen = 100, errno = 206)
    @JSONField(name = "description")
    @QueryKey(value = "Stu_Description")
    private String description;

    @ValidateKey(maxRange = 4, errno = 207)
    @JSONField(name = "type")
    @QueryKey(value = "Stu_Type")
    private int type;

    @ValidateKey(maxRange = 20, errno = 208)
    @JSONField(name = "rows")
    @QueryKey(value = "Stu_Rows")
    private int row;

    @ValidateKey(maxRange = 20, errno = 209)
    @JSONField(name = "cols")
    @QueryKey(value = "Stu_cols")
    private int column;

    public Studio(int id, String name, String description, int type, int row, int column) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.type = type;
        this.row = row;
        this.column = column;
    }

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
