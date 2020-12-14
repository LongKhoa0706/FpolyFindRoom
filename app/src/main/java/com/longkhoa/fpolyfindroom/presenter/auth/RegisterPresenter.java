package com.longkhoa.fpolyfindroom.presenter.auth;

import android.util.Log;

import com.google.gson.Gson;
import com.longkhoa.fpolyfindroom.model.User;
import com.longkhoa.fpolyfindroom.networking.RetrofitClient;
import com.longkhoa.fpolyfindroom.service.AuthService;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

// Tầng Presenter là nơi đảm nhiệm xử lý logic vd đăng ký đăng nhập, get data
public class RegisterPresenter {
    private RegisterInterface registerInterface;
    AuthService authService;

    class Result {
        int statusCode;
        String message;
    }

    public RegisterPresenter(RegisterInterface registerInterface) {
        this.registerInterface = registerInterface;
    }

    public void register(User user) {
        authService = RetrofitClient.getRetrofitInstance().create(AuthService.class);
        authService.registerUser(user.getPhone(),user.getPassword(),user.getRoles().getRoleName(),user.getEmail(),user.getName()).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                Log.d("KETQUA1",response.message());
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.d("KETQUA",t.getMessage());
            }
        });
    }

}
