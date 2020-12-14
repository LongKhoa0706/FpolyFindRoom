package com.longkhoa.fpolyfindroom.model;

public class Intro {
    private int image_intro;
    private String txt_title;

    public int getImage_intro() {
        return image_intro;
    }

    public void setImage_intro(int image_intro) {
        this.image_intro = image_intro;
    }

    public String getTxt_title() {
        return txt_title;
    }

    public void setTxt_title(String txt_title) {
        this.txt_title = txt_title;
    }

    public Intro(int image_intro, String txt_title) {
        this.image_intro = image_intro;
        this.txt_title = txt_title;
    }

    public Intro() {
    }
}
