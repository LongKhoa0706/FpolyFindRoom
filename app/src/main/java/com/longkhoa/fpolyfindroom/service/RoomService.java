package com.longkhoa.fpolyfindroom.service;

import com.longkhoa.fpolyfindroom.model.City;
import com.longkhoa.fpolyfindroom.model.MyResultCity;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface RoomService {

    @GET("rooms")
    Call<ResponseBody> getListRoom();

    @POST("create-room")
    Call<ResponseBody> createRoom();

}
