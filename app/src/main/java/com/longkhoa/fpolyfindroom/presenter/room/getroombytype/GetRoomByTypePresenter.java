package com.longkhoa.fpolyfindroom.presenter.room.getroombytype;

import android.util.Log;

import com.longkhoa.fpolyfindroom.model.MyStatusRoom;
import com.longkhoa.fpolyfindroom.model.Room;
import com.longkhoa.fpolyfindroom.networking.RetrofitClient;
import com.longkhoa.fpolyfindroom.service.RoomService;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GetRoomByTypePresenter {
    RoomService roomService;
    GetRoomByTypeInterface getRoomByTypeInterface;

    public GetRoomByTypePresenter(GetRoomByTypeInterface getRoomByTypeInterface) {
        this.getRoomByTypeInterface = getRoomByTypeInterface;
    }

    public void getListRoomByType(String type){
        roomService = RetrofitClient.getRetrofitInstance().create(RoomService.class);
        roomService.getListRoomByType(type).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                Log.d("KETQUA",response.message());
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.d("KETQUA",t.getMessage());

            }
        });
    }

}

