package com.longkhoa.fpolyfindroom.model;

public class Util {
    private String title;
    private int icon;
    private boolean isCheck;

    public Util(String title, int icon) {
        this.title = title;
        this.icon = icon;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getIcon() {
        return icon;
    }

    public boolean isCheck() {
        return isCheck;
    }

    public void setCheck(boolean check) {
        isCheck = check;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }
}
