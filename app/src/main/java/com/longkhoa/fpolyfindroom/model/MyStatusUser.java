package com.longkhoa.fpolyfindroom.model;

import com.google.gson.annotations.SerializedName;

public class MyStatusUser {
    @SerializedName("statusCode")
   private int statusCode;

    @SerializedName("message")
    private   String mes;

    @SerializedName("success")
    private  boolean success;

    @SerializedName("data")
    private User user;




    public int getStatusCode() {
        return statusCode;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getMes() {
        return mes;
    }

    public void setMes(String mes) {
        this.mes = mes;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
