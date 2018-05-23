package com.hepangda.ttms.model;

import com.alibaba.fastjson.annotation.JSONField;

public class User {
    @JSONField(name="id")
    private int id;

    @JSONField(name="name")
    private String name;

    @JSONField(name="password")
    private String password;

    @JSONField(name="age")
    private short age;

    @JSONField(name="privilege")
    private short privilege;
}
