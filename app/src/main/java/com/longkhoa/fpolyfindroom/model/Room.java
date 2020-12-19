package com.longkhoa.fpolyfindroom.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Room implements Parcelable {
    @SerializedName("coordinates")
    @Expose
    private List<Double> coordinates = null;
    @SerializedName("image")
    @Expose
    private List<String> image = null;
    @SerializedName("status")
    @Expose
    private boolean status;
    @SerializedName("utilities")
    @Expose
    private List<String> utilities = null;
    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("location")
    @Expose
    private String location;
    @SerializedName("lng")
    @Expose
    private double lng;
    @SerializedName("lat")
    @Expose
    private double lat;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("price")
    @Expose
    private int price;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("author")
    @Expose
    private User user;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;
    @SerializedName("amount_room")
    @Expose
    private int amount_room;
    @SerializedName("amount_bathroom")
    @Expose
    private int amount_bathroom;
    @SerializedName("amount_bedroom")
    @Expose
    private int amount_bedroom;
    @SerializedName("acreage")
    @Expose
    private int acreage;



    public Room(List<String> image, boolean status, List<String> utilities, String id, String type, String location, String description, int price, String title, User user, String createdAt, String updatedAt,double lat,double lng,int amount_room,int amount_bathroom,int amount_bedroom,int acreage) {
        this.image = image;
        this.status = status;
        this.utilities = utilities;
        this.id = id;
        this.type = type;
        this.location = location;
        this.description = description;
        this.price = price;
        this.title = title;
        this.user = user;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.lat = lat;
        this.lng = lng;
        this.acreage = acreage;
        this.amount_bathroom = amount_bathroom;
        this.amount_bedroom = amount_bedroom;
        this.amount_room = amount_room;
    }

    public Room() {
    }


    public List<Double> getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(List<Double> coordinates) {
        this.coordinates = coordinates;
    }

    protected Room(Parcel in) {
        image = in.createStringArrayList();
        status = in.readByte() != 0;
        utilities = in.createStringArrayList();
        id = in.readString();
        type = in.readString();
        location = in.readString();
        lng = in.readDouble();
        lat = in.readDouble();
        description = in.readString();
        price = in.readInt();
        title = in.readString();
        createdAt = in.readString();
        updatedAt = in.readString();
    }

    public static final Creator<Room> CREATOR = new Creator<Room>() {
        @Override
        public Room createFromParcel(Parcel in) {
            return new Room(in);
        }

        @Override
        public Room[] newArray(int size) {
            return new Room[size];
        }
    };

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public List<String> getImage() {
        return image;
    }

    public void setImage(List<String> image) {
        this.image = image;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public List<String> getUtilities() {
        return utilities;
    }

    public void setUtilities(List<String> utilities) {
        this.utilities = utilities;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }


    public int getAmount_room() {
        return amount_room;
    }

    public void setAmount_room(int amount_room) {
        this.amount_room = amount_room;
    }

    public int getAmount_bathroom() {
        return amount_bathroom;
    }

    public void setAmount_bathroom(int amount_bathroom) {
        this.amount_bathroom = amount_bathroom;
    }

    public int getAmount_bedroom() {
        return amount_bedroom;
    }

    public void setAmount_bedroom(int amount_bedroom) {
        this.amount_bedroom = amount_bedroom;
    }

    public int getAcreage() {
        return acreage;
    }

    public void setAcreage(int acreage) {
        this.acreage = acreage;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {

    }
}
