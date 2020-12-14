package com.longkhoa.fpolyfindroom.service;

import com.longkhoa.fpolyfindroom.model.MyStatus;
import com.longkhoa.fpolyfindroom.model.MyStatusRoom;
import com.longkhoa.fpolyfindroom.model.Room;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;

public interface RoomService {

    @GET("rooms")
    Call<ResponseBody> getListRoom();

}
