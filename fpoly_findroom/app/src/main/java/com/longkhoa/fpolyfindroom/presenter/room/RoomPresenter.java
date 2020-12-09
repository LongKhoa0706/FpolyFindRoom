package com.longkhoa.fpolyfindroom.presenter.room;

import android.util.Log;

import com.google.gson.Gson;
import com.longkhoa.fpolyfindroom.model.MyStatus;
import com.longkhoa.fpolyfindroom.model.MyStatusRoom;
import com.longkhoa.fpolyfindroom.model.Room;
import com.longkhoa.fpolyfindroom.networking.RetrofitClient;
import com.longkhoa.fpolyfindroom.service.RoomService;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RoomPresenter {
    RoomService roomService;
    RoomInterface roomInterface;

    public RoomPresenter(RoomInterface roomInterface) {
        this.roomInterface = roomInterface;
    }

    public void getListRoomm(){
        roomService = RetrofitClient.getRetrofitInstance().create(RoomService.class);
        roomService.getListRoom().enqueue(new Callback<ResponseBody>() {
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
//        roomService.getListRoom().enqueue(new Callback<List<Room>>() {
//            @Override
//            public void onResponse(Call<List<Room>> call, Response<List<Room>> response) {
//                Log.d("DATAAAAAAAA",response.message());
////                roomInterface.getListRoom(response.body());
//            }
//
//            @Override
//            public void onFailure(Call<List<Room>> call, Throwable t) {
//                Log.d("LOIIIIIIII nha",t.getMessage());
//            }
//        });
    }

}
