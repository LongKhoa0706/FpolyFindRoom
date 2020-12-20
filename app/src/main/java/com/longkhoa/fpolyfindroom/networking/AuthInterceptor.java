package com.longkhoa.fpolyfindroom.networking;
import android.util.Log;

import com.longkhoa.fpolyfindroom.service.AuthService;
import com.longkhoa.fpolyfindroom.view.activity.ClientActivity;
import com.longkhoa.fpolyfindroom.view.auth.LoginFragment;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Retrofit;

public class AuthInterceptor implements Interceptor {

    public static String token ;

    @Override
    public Response intercept(Chain chain) throws IOException {
        //rewrite the request to add bearer token
        Request newRequest=chain.request().newBuilder()
                .header("Authorization","")
                .build();

        return chain.proceed(newRequest);
    }
}
