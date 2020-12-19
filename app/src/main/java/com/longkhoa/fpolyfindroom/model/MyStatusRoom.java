package com.longkhoa.fpolyfindroom.model;//package com.longkhoa.fpolyfindroom.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.longkhoa.fpolyfindroom.model.User;

import java.util.ArrayList;
import java.util.List;

public class MyStatusRoom {
    @SerializedName("statusCode")
    @Expose
    private int statusCode;
    @SerializedName("success")
    @Expose
    private boolean success;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("data")
    @Expose
    private ArrayList<Room> data = null;

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ArrayList<Room> getData() {
        return data;
    }

    public void setData(ArrayList<Room> data) {
        this.data = data;
    }

}

