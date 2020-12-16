package com.longkhoa.fpolyfindroom.service;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface UserService {
    @GET("profile")
    Call<ResponseBody> getProfile();

    @POST("update-profile")
    Call<ResponseBody>update(@Field("name") String name, @Field("email")  String email,@Field("phone")String phone);

}
