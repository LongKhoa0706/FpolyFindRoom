package com.longkhoa.fpolyfindroom.networking;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;



import java.io.IOException;
import java.util.HashSet;
import java.util.logging.Logger;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

// cấu hình retrofit, NHỚ ĐỔI URL VÀ NHỚ URL LÀ 192.168.0.108:5000 trong đó 192.168.0.108  đường dẫn địa chỉ wifi
public class RetrofitClient {
//    private static Context context;

    private static Retrofit retrofit;
    public static final String BASE_URL = "http://192.168.0.101:5000/";
    public static Retrofit getRetrofitInstance() {
        if (retrofit == null) {
//
//            TokenInterceptor interceptor=new TokenInterceptor(context);
//
//            OkHttpClient client = new OkHttpClient.Builder()
//                    .addInterceptor(interceptor).build();

            retrofit = new retrofit2.Retrofit.Builder()
//                    .client(client)
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
