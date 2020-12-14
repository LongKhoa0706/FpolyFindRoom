package com.longkhoa.fpolyfindroom.model;

public class Favorite {
    private int image_favorite, image_icon;
    private String txt_title, txt_district, txt_price;

    public int getImage_favorite() {
        return image_favorite;
    }

    public void setImage_favorite(int image_favorite) {
        this.image_favorite = image_favorite;
    }

    public int getImage_icon() {
        return image_icon;
    }

    public void setImage_icon(int image_icon) {
        this.image_icon = image_icon;
    }

    public String getTxt_title() {
        return txt_title;
    }

    public void setTxt_title(String txt_title) {
        this.txt_title = txt_title;
    }

    public String getTxt_district() {
        return txt_district;
    }

    public void setTxt_district(String txt_district) {
        this.txt_district = txt_district;
    }

    public String getTxt_price() {
        return txt_price;
    }

    public void setTxt_price(String txt_price) {
        this.txt_price = txt_price;
    }

    public Favorite(int image_favorite, int image_icon, String txt_title, String txt_district, String txt_price) {
        this.image_favorite = image_favorite;
        this.image_icon = image_icon;
        this.txt_title = txt_title;
        this.txt_district = txt_district;
        this.txt_price = txt_price;
    }

    public Favorite() {
    }
}
