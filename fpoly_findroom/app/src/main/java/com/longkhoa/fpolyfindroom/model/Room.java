package com.longkhoa.fpolyfindroom.model;

import java.util.List;

public class Room {
    private String price;
    private String categoriesRoom;
    private String address;
    private String datetime;
    private List<String> listImage;
    private String description;
    private boolean status;
    private User user;
    private String title;
    private String categoriesDistrict;

    public Room(String price, String categoriesRoom, String address, String datetime, List<String> listImage, String description, boolean status, User user, String title, String categoriesDistrict) {
        this.price = price;
        this.categoriesRoom = categoriesRoom;
        this.address = address;
        this.datetime = datetime;
        this.listImage = listImage;
        this.description = description;
        this.status = status;
        this.user = user;
        this.title = title;
        this.categoriesDistrict = categoriesDistrict;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getCategoriesRoom() {
        return categoriesRoom;
    }

    public void setCategoriesRoom(String categoriesRoom) {
        this.categoriesRoom = categoriesRoom;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    public List<String> getListImage() {
        return listImage;
    }

    public void setListImage(List<String> listImage) {
        this.listImage = listImage;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCategoriesDistrict() {
        return categoriesDistrict;
    }

    public void setCategoriesDistrict(String categoriesDistrict) {
        this.categoriesDistrict = categoriesDistrict;
    }
}
