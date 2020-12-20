package com.longkhoa.fpolyfindroom.presenter.auth;

import android.util.Log;

import com.google.gson.Gson;
import com.longkhoa.fpolyfindroom.model.MyStatusUser;
import com.longkhoa.fpolyfindroom.networking.RetrofitClient;
import com.longkhoa.fpolyfindroom.service.AuthService;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginPresenter {
    private AuthService authService;
    LoginInterface loginInterface;


    public LoginPresenter(LoginInterface loginInterface) {
        this.loginInterface = loginInterface;
    }

    public void login(String phone,String password){
        authService = RetrofitClient.getRetrofitInstance().create(AuthService.class);
        authService.login(phone,password).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (phone !=null || password !=null){
                    try {
                        if (response.body() !=null){
                            String jsonString = response.body().string();
                            Gson gson = new Gson();
                            MyStatusUser status = gson.fromJson(jsonString, MyStatusUser.class);
//                            Log.d("TOKENT",status.getUser().getToken());
                            loginInterface.loginSuccess(status);
                        }else {
                            String jsonString = response.errorBody().string();
                            Gson gson = new Gson();
                            MyStatusUser status = gson.fromJson(jsonString, MyStatusUser.class);
                            loginInterface.loginFail(status.getMes());
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }else {
                    Log.d("SAIIII","vo day ne");
                    loginInterface.loginFail("Vui lòng nhập đủ thông tin");
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                loginInterface.loginFail(t.getMessage());
            }
        });
    }

}
