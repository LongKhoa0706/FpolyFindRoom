package com.longkhoa.fpolyfindroom.service;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;

public interface UserService {
    @GET("profile")
    Call<ResponseBody> getProfile();

}
