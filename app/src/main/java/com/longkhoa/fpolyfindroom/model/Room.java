package com.longkhoa.fpolyfindroom.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Room implements Parcelable {
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


    public Room(List<String> image, boolean status, List<String> utilities, String id, String type, String location, String description, int price, String title, User user, String createdAt, String updatedAt) {
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

    public static Creator<Room> getCREATOR() {
        return CREATOR;
    }

    protected Room(Parcel in) {
        image = in.createStringArrayList();
        status = in.readByte() != 0;
        utilities = in.createStringArrayList();
        id = in.readString();
        type = in.readString();
        location = in.readString();
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeStringList(image);
        parcel.writeByte((byte) (status ? 1 : 0));
        parcel.writeStringList(utilities);
        parcel.writeString(id);
        parcel.writeString(type);
        parcel.writeString(location);
        parcel.writeString(description);
        parcel.writeInt(price);
        parcel.writeString(title);
        parcel.writeString(createdAt);
        parcel.writeString(updatedAt);
    }
}
