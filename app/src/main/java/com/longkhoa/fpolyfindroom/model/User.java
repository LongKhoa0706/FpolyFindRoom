package com.longkhoa.fpolyfindroom.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class User {
    @SerializedName("verify")
    @Expose
    private boolean verify;
    @SerializedName("avatar")
    @Expose
    private String avatar;
    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("phone")
    @Expose
    private String phone;
    @SerializedName("password")
    @Expose
    private String password;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("roles")
    @Expose
    private Role roles;
    @SerializedName("create_at")
    @Expose
    private String createAt;
    @SerializedName("update_at")
    @Expose
    private String updateAt;
    @SerializedName("token")
    private String token;


    public User(boolean verify, String avatar, String id, String phone, String password, String email, String name, Role roles, String createAt, String updateAt, String token) {
        this.verify = verify;
        this.avatar = avatar;
        this.id = id;
        this.phone = phone;
        this.password = password;
        this.email = email;
        this.name = name;
        this.roles = roles;
        this.createAt = createAt;
        this.updateAt = updateAt;
        this.token = token;
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Role getRoles() {
        return roles;
    }

    public void setRoles(Role roles) {
        this.roles = roles;
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

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
