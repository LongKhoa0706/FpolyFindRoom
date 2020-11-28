package com.longkhoa.fpolyfindroom.presenter;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.longkhoa.fpolyfindroom.model.User;
import com.longkhoa.fpolyfindroom.networking.RetrofitClient;
import com.longkhoa.fpolyfindroom.service.RegisterService;

import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

// Tầng Presenter là nơi đảm nhiệm xử lý logic vd đăng ký đăng nhập, get data
public class RegisterPresenter {
    private  RegisterInterface registerInterface;
     RegisterService registerService;

    class Result{
        int statusCode;
        String message;
    }

    public RegisterPresenter(RegisterInterface registerInterface) {
        this.registerInterface = registerInterface;
    }

    public void register(User user){
        registerService = RetrofitClient.getRetrofitInstance().create(RegisterService.class);
            Call<ResponseBody> jsonObjectCall = registerService.registerUser(user);
        jsonObjectCall.enqueue(new Callback<ResponseBody>() {
              @Override
              public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                  if (response.isSuccessful()){
//                      try {
//                          JSONObject json = new JSONObject(response.body().toString());
//                          Log.i("KET QUA", json.getString("message"));
//                          registerInterface.registerSuccess();
//                      } catch (JSONException e) {
//                          e.printStackTrace();
//                      }

//                      String jsonString = response.body();

                      Gson gson = new Gson();

//                      Result status = gson.fromJson(jsonString, Result.class);
//                      Log.i("KET QUA", call.);
                      registerInterface.registerSuccess();


                  }else {

                      registerInterface.registerFail(response.message());

                  }

              }

              @Override
              public void onFailure(Call<ResponseBody> call, Throwable t) {
                  Log.d("dasds",t.getMessage());
//                  registerInterface.registerFail(t.getMessage());
              }
          });


//        if (username.isEmpty()){
//            registerInterface.registerFail();
//        }else {
//
//            registerService.registerUser(user);
//            registerInterface.registerSuccess();
//        }
    }

}
