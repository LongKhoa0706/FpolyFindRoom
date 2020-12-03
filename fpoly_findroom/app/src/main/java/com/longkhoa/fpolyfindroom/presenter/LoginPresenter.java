package com.longkhoa.fpolyfindroom.presenter;

import android.util.Log;

import com.longkhoa.fpolyfindroom.networking.RetrofitClient;
import com.longkhoa.fpolyfindroom.service.AuthService;

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
                if (response.isSuccessful()){
                    loginInterface.loginSuccess();
                }else {
                    Log.d("LOGIN",response.message());
                  loginInterface.loginFail(response.message().toString());
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }

}
