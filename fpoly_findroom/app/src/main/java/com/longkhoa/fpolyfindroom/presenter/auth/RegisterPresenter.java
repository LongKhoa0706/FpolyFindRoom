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
        authService.registerUser(user).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful()) {
//                    try {
//                        if (response.body() != null) {
//                            String jsonString = response.body();
//                            Log.i("Jsonnnnn", jsonString);
//                            Gson gson = new Gson();
//                            Result status = gson.fromJson(jsonString, Result.class);
//                            Log.i("KET QUA", String.valueOf(status.message) + "");
//                            registerInterface.registerSuccess();
//                        } else {
//                            Log.i("Jsonnnnn", "FAIL");
//                        }
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                    Log.d("======>");
                    registerInterface.registerSuccess();
                } else {
                    Log.i("KET QUA FAIL", String.valueOf(response.message()) + "");
//                          registerInterface.registerFail(status.message);
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

            }
        });
    }

}
