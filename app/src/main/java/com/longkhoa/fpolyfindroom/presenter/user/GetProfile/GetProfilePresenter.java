package com.longkhoa.fpolyfindroom.presenter.user.GetProfile;

import android.util.Log;

import com.google.gson.Gson;
import com.longkhoa.fpolyfindroom.model.MyStatusUser;
import com.longkhoa.fpolyfindroom.networking.RetrofitClient;
import com.longkhoa.fpolyfindroom.service.UserService;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GetProfilePresenter {
    UserService userService;
    GetProfileInterface getProfileInterface;

    public GetProfilePresenter(GetProfileInterface getProfileInterface) {
        this.getProfileInterface = getProfileInterface;
    }

    public void getProfile(String token){
        userService = RetrofitClient.getRetrofitInstance().create(UserService.class);
        userService.getProfile(token).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    if (response.body() !=null){
                        String jsonString = response.body().string();
                        Gson gson = new Gson();
                        MyStatusUser status = gson.fromJson(jsonString, MyStatusUser.class);
                        getProfileInterface.getProfileSuccess(status);
                    }else {
//                        String jsonString = response.errorBody().string();
//                        Gson gson = new Gson();
//                        MyStatusUser status = gson.fromJson(jsonString, MyStatusUser.class);
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
