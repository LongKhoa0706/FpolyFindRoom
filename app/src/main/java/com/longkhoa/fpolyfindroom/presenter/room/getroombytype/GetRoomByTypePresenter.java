package com.longkhoa.fpolyfindroom.presenter.room.getroombytype;

import android.util.Log;

import com.google.gson.Gson;
import com.longkhoa.fpolyfindroom.model.MyStatusRoom;
import com.longkhoa.fpolyfindroom.model.Room;
import com.longkhoa.fpolyfindroom.networking.RetrofitClient;
import com.longkhoa.fpolyfindroom.service.RoomService;

import java.io.IOException;

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

    public void getListRoomByType(String token,String type){
        roomService = RetrofitClient.getRetrofitInstance().create(RoomService.class);
        roomService.getListRoomByType(token,type).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    if (response.body() !=null){
                        String jsonString = response.body().string();
                        Gson gson = new Gson();
                        MyStatusRoom status = gson.fromJson(jsonString, MyStatusRoom.class);
                        getRoomByTypeInterface.getRoomByType(status);

                    }else {
                        String jsonString = response.errorBody().string();
                        Gson gson = new Gson();
                        MyStatusRoom status = gson.fromJson(jsonString, MyStatusRoom.class);
//                        loginInterface.loginFail(status.getMes());
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.d("KETQUA",t.getMessage());

            }
        });
    }

}

