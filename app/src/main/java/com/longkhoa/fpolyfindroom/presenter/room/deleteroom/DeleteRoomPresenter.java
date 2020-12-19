package com.longkhoa.fpolyfindroom.presenter.room.deleteroom;

import android.util.Log;

import com.longkhoa.fpolyfindroom.networking.RetrofitClient;
import com.longkhoa.fpolyfindroom.service.RoomService;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DeleteRoomPresenter {
    RoomService roomService;

    public void deleteRoom(String idRoom){
        roomService = RetrofitClient.getRetrofitInstance().create(RoomService.class);
        roomService.deleteRoom(idRoom).enqueue(new Callback<ResponseBody>() {
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
