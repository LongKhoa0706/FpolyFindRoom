package com.longkhoa.fpolyfindroom.service;

import com.longkhoa.fpolyfindroom.model.User;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

// file service này là nơi xử lý gọi api  thêm xoá sửa , get data
public interface RegisterService {

    @POST("register")
    Call<ResponseBody>registerUser(@Body User user);

}
