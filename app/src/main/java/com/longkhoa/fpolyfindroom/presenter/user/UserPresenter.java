package com.longkhoa.fpolyfindroom.presenter.user;

import android.util.Log;

import com.google.gson.Gson;
import com.longkhoa.fpolyfindroom.model.User;
import com.longkhoa.fpolyfindroom.networking.RetrofitClient;
import com.longkhoa.fpolyfindroom.presenter.auth.RegisterInterface;
import com.longkhoa.fpolyfindroom.service.UserService;
import com.longkhoa.fpolyfindroom.view.profile.ProfileFragment;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserPresenter {
    private UserService userService;
    private UserInterface userInterface;

    public UserPresenter(UserInterface userInterface) {
        this.userInterface = userInterface;
    }

    public void update (User user){
        userService = RetrofitClient.getRetrofitInstance().create(UserService.class);
        userService.update(user.getName(),user.getEmail(),user.getPhone()).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                Log.d("update thành công",response.message());
            }
            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.d("abc",t.getMessage());
            }
        });

    }
}
