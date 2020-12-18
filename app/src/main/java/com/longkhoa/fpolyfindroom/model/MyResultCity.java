package com.longkhoa.fpolyfindroom.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MyResultCity {
    @SerializedName("LtsItem")
    @Expose
    private List<City> ltsItem = null;

    public List<City> getLtsItem() {
        return ltsItem;
    }

    public void setLtsItem(List<City> ltsItem) {
        this.ltsItem = ltsItem;
    }

}