package com.longkhoa.fpolyfindroom.model;

public class District {
    private int image_district;
    private String txt2;

    public District() {
    }

    public int getImage_district() {
        return image_district;
    }

    public void setImage_district(int image_district) {
        this.image_district = image_district;
    }

    public String getTxt2() {
        return txt2;
    }

    public void setTxt2(String txt2) {
        this.txt2 = txt2;
    }

    public District(int image_district, String txt2) {
        this.image_district = image_district;
        this.txt2 = txt2;
    }
}
