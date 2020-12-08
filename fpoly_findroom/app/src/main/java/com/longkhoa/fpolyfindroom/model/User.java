package com.longkhoa.fpolyfindroom.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class User implements Parcelable {
    @SerializedName("_id")
    private String id;

    @SerializedName("roles")
    private List<String> roles;

    @SerializedName("email")
    private String email;

    @SerializedName("phone")
    private String phone;

    @SerializedName("userName")
    private String userName;

    @SerializedName("password")
    private String password;

    @SerializedName("create_at")
    private String createAt;

    @SerializedName("update_at")
    private String updateAt;

    @SerializedName("verify")
    private boolean verify;

    @SerializedName("avatar")
    private String avatar;

    @SerializedName("token")
    private String token;

    protected User(Parcel in) {
        id = in.readString();
        roles = in.createStringArrayList();
        email = in.readString();
        phone = in.readString();
        userName = in.readString();
        password = in.readString();
        createAt = in.readString();
        updateAt = in.readString();
        verify = in.readByte() != 0;
        avatar = in.readString();
        token = in.readString();
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    public String getToken() {
        return token;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public User(String email, String phone, String userName, String password, String createAt, String updateAt, boolean verify, String avatar) {
        this.email = email;
        this.phone = phone;
        this.userName = userName;
        this.password = password;
        this.createAt = createAt;
        this.updateAt = updateAt;
        this.verify = verify;
        this.avatar = avatar;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCreateAt() {
        return createAt;
    }

    public void setCreateAt(String createAt) {
        this.createAt = createAt;
    }

    public String getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(String updateAt) {
        this.updateAt = updateAt;
    }

    public boolean isVerify() {
        return verify;
    }

    public void setVerify(boolean verify) {
        this.verify = verify;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(id);
        parcel.writeStringList(roles);
        parcel.writeString(email);
        parcel.writeString(phone);
        parcel.writeString(userName);
        parcel.writeString(password);
        parcel.writeString(createAt);
        parcel.writeString(updateAt);
        parcel.writeByte((byte) (verify ? 1 : 0));
        parcel.writeString(avatar);
        parcel.writeString(token);
    }
}
