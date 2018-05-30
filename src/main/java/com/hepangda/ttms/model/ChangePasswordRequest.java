package com.hepangda.ttms.model;

import com.alibaba.fastjson.annotation.JSONField;

public class ChangePasswordRequest {
    @JSONField(name = "uid")
    private int uid;

    @JSONField(name = "oldpwd")
    private String oldPassword;

    @JSONField(name = "newpwd")
    private String newPassword;

    public ChangePasswordRequest(int uid, String oldPassword, String newPassword) {
        this.uid = uid;
        this.oldPassword = oldPassword;
        this.newPassword = newPassword;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
}
