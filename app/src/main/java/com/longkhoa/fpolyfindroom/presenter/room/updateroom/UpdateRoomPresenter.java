package com.longkhoa.fpolyfindroom.presenter.room.updateroom;

import android.util.Log;

import com.longkhoa.fpolyfindroom.networking.RetrofitClient;
import com.longkhoa.fpolyfindroom.service.RoomService;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UpdateRoomPresenter {
    RoomService roomService;

    public void updateRoom(String idRoom,int price,String token){
        roomService = RetrofitClient.getRetrofitInstance().create(RoomService.class);
        roomService.updateRoom(token,idRoom,price).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                Log.d("KETQUA",idRoom);

                Log.d("KETQUA",response.message());
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.d("KETQUA",t.getMessage());
            }
        });
    }

}
