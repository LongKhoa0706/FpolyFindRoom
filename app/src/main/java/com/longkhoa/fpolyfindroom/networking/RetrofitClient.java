package com.longkhoa.fpolyfindroom.networking;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;



import java.io.IOException;
import java.util.HashSet;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import okhttp3.Interceptor;
import okhttp3.OkHttp;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    private static Retrofit retrofit;

    public static final String BASE_URL = "http://192.168.10.155:8080/";

    public static Retrofit getRetrofitInstance() {
        if (retrofit == null) {
            OkHttpClient okHttpClient = new OkHttpClient.Builder()
                    .addInterceptor(new LoggingInterceptor())
                    .addInterceptor(new AuthInterceptor())
                    .addNetworkInterceptor( new LoggingInterceptor())
                    .connectTimeout(1, TimeUnit.MINUTES)
                    .readTimeout(30, TimeUnit.SECONDS)
                    .writeTimeout(15, TimeUnit.SECONDS)
                    .build();
            retrofit = new retrofit2.Retrofit.Builder()
//                    .client(client)
                    .client(okHttpClient)
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
//class TokenInterceptor implements Interceptor{
//    Context context;
//
//    public TokenInterceptor(Context context) {
//        this.context = context;
//    }
//
//    @Override
//    public Response intercept(Chain chain) throws IOException {
//        Request newRequest=chain.request().newBuilder()
//                .header("Authorization","Bearer "+ SharedPre.getInstance(context).get("Token"))
//                .build();
//
//        return chain.proceed(newRequest);
//    }
//}
