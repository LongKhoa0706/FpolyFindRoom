package com.longkhoa.fpolyfindroom.service;

import com.longkhoa.fpolyfindroom.model.City;
import com.longkhoa.fpolyfindroom.model.MyResultCity;
import com.longkhoa.fpolyfindroom.model.Room;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface RoomService {

    @GET("rooms")
    Call<ResponseBody> getListRoom();

    @POST("create-room")
    Call<ResponseBody> createRoom(@Body Room room);

    @GET("my-rooms")
    Call<ResponseBody> getMyRoom();

    @GET("rooms")
    Call<ResponseBody> getListRoomByType(@Query("type") String type);

}
