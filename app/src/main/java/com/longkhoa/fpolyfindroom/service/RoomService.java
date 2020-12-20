package com.longkhoa.fpolyfindroom.service;

import com.longkhoa.fpolyfindroom.model.City;
import com.longkhoa.fpolyfindroom.model.MyResultCity;
import com.longkhoa.fpolyfindroom.model.Room;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface RoomService {

    @GET("rooms")
    Call<ResponseBody> getListRoom(@Header("Authorization") String token);

    @POST("create-room")
    Call<ResponseBody> createRoom(@Header("Authorization") String token,@Body Room room);

    @GET("my-rooms")
    Call<ResponseBody> getMyRoom(@Header("Authorization") String token);

    @GET("rooms")
    Call<ResponseBody> getListRoomByType(@Header("Authorization") String token,@Query("type") String type);

    @GET("delete-room/{id}")
    Call<ResponseBody> deleteRoom(@Header("Authorization") String token,@Path("id") String idRoom);

    @FormUrlEncoded
    @POST("update-room/{id}")
    Call<ResponseBody> updateRoom(@Header("Authorization") String token,@Path("id") String idRoom, @Field("price") int price);

}
