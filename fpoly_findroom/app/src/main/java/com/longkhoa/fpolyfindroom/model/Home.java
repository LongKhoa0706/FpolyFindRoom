package com.longkhoa.fpolyfindroom.model;

public class Home {
    private int image_home, image_home1, image_favorite,image_favorite1;
    private String txt_loai, txt_title, txt_price, txt_loai1, txt_title1, txt_price1;

    public int getImage_home() {
        return image_home;
    }

    public void setImage_home(int image_home) {
        this.image_home = image_home;
    }

    public int getImage_home1() {
        return image_home1;
    }

    public void setImage_home1(int image_home1) {
        this.image_home1 = image_home1;
    }

    public int getImage_favorite() {
        return image_favorite;
    }

    public void setImage_favorite(int image_favorite) {
        this.image_favorite = image_favorite;
    }

    public int getImage_favorite1() {
        return image_favorite1;
    }

    public void setImage_favorite1(int image_favorite1) {
        this.image_favorite1 = image_favorite1;
    }

    public String getTxt_loai() {
        return txt_loai;
    }

    public void setTxt_loai(String txt_loai) {
        this.txt_loai = txt_loai;
    }

    public String getTxt_title() {
        return txt_title;
    }

    public void setTxt_title(String txt_title) {
        this.txt_title = txt_title;
    }

    public String getTxt_price() {
        return txt_price;
    }

    public void setTxt_price(String txt_price) {
        this.txt_price = txt_price;
    }

    public String getTxt_loai1() {
        return txt_loai1;
    }

    public void setTxt_loai1(String txt_loai1) {
        this.txt_loai1 = txt_loai1;
    }

    public String getTxt_title1() {
        return txt_title1;
    }

    public void setTxt_title1(String txt_title1) {
        this.txt_title1 = txt_title1;
    }

    public String getTxt_price1() {
        return txt_price1;
    }

    public void setTxt_price1(String txt_price1) {
        this.txt_price1 = txt_price1;
    }

    public Home(int image_home, int image_home1, int image_favorite, int image_favorite1, String txt_loai, String txt_title, String txt_price, String txt_loai1, String txt_title1, String txt_price1) {
        this.image_home = image_home;
        this.image_home1 = image_home1;
        this.image_favorite = image_favorite;
        this.image_favorite1 = image_favorite1;
        this.txt_loai = txt_loai;
        this.txt_title = txt_title;
        this.txt_price = txt_price;
        this.txt_loai1 = txt_loai1;
        this.txt_title1 = txt_title1;
        this.txt_price1 = txt_price1;
    }

    public Home() {
    }
}
