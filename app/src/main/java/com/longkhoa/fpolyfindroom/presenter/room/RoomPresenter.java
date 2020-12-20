package com.longkhoa.fpolyfindroom.presenter.room;

import android.util.Log;

import com.google.gson.Gson;
import com.longkhoa.fpolyfindroom.model.City;
import com.longkhoa.fpolyfindroom.model.MyResultCity;
import com.longkhoa.fpolyfindroom.model.MyStatusRoom;
import com.longkhoa.fpolyfindroom.networking.RetrofitClient;
import com.longkhoa.fpolyfindroom.service.RoomService;

import java.io.IOException;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.converter.gson.GsonConverterFactory;

public class RoomPresenter {
    RoomService roomService;
    RoomInterface roomInterface;

    public RoomPresenter(RoomInterface roomInterface) {
        this.roomInterface = roomInterface;
    }

    public void getListRoomm(String token){
        roomService = RetrofitClient.getRetrofitInstance().create(RoomService.class);
        roomService.getListRoom( token).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()){
                    try {
                        String jsonString = response.body().string();
                        Gson gson = new Gson();
                        MyStatusRoom status = gson.fromJson(jsonString,MyStatusRoom.class);
//                        Log.d("KETTT qua ",status.getData().get(0).getTitle());
                        roomInterface.getListRoom(status);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }else {
                    try {
                        Log.d("LOI",response.errorBody().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.d("VBBBBBB",t.getMessage());
            }
        });
    }



}
