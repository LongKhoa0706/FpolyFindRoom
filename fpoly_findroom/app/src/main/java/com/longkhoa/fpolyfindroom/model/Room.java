package com.longkhoa.fpolyfindroom.model;

public class Room {
    private int image_room, image_favorite, image_room1;
    private String txt_loai, txt_title, txt_price,txt_loai1, txt_title1, txt_price1;

    public Room() {
    }

    public int getImage_room() {
        return image_room;
    }

    public void setImage_room(int image_room) {
        this.image_room = image_room;
    }

    public int getImage_favorite() {
        return image_favorite;
    }

    public void setImage_favorite(int image_favorite) {
        this.image_favorite = image_favorite;
    }

    public int getImage_room1() {
        return image_room1;
    }

    public void setImage_room1(int image_room1) {
        this.image_room1 = image_room1;
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

    public Room(int image_room, int image_favorite, int image_room1, String txt_loai, String txt_title, String txt_price, String txt_loai1, String txt_title1, String txt_price1) {
        this.image_room = image_room;
        this.image_favorite = image_favorite;
        this.image_room1 = image_room1;
        this.txt_loai = txt_loai;
        this.txt_title = txt_title;
        this.txt_price = txt_price;
        this.txt_loai1 = txt_loai1;
        this.txt_title1 = txt_title1;
        this.txt_price1 = txt_price1;
    }
}
