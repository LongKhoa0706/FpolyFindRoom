package com.longkhoa.fpolyfindroom.networking;
import android.util.Log;

import com.longkhoa.fpolyfindroom.service.AuthService;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Retrofit;

public class AuthInterceptor implements Interceptor {
    private static String token = null;

    @Override
    public Response intercept(Chain chain) throws IOException {

        //rewrite the request to add bearer token
        Request newRequest=chain.request().newBuilder()
                .header("Authorization","eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VySWQiOiI1ZmNmNDhiMjFmMjk3ZTIyZGIzNjNkYmUiLCJwaG9uZSI6IjAxMjM0NTY3ODkwIiwiaWF0IjoxNjA3NDIxOTQ3LCJleHAiOjE2MTAwMTM5NDd9.WF8bLztBVr12Gc4gYhjsB52zXNt02gNeihd_XmyMz_s")
                .build();

        return chain.proceed(newRequest);
    }
}
