package com.longkhoa.fpolyfindroom.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class City {
    @SerializedName("ID")
    @Expose
    private Integer iD;
    @SerializedName("Title")
    @Expose
    private String title;

    public Integer getID() {
        return iD;
    }

    public void setID(Integer iD) {
        this.iD = iD;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
