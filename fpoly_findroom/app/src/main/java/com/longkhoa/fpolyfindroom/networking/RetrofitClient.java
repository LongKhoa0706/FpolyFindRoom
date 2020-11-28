package com.longkhoa.fpolyfindroom.networking;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

// cấu hình retrofit, NHỚ ĐỔI URL VÀ NHỚ URL LÀ 192.168.0.108:5000 trong đó 192.168.0.108  đường dẫn địa chỉ wifi
public class RetrofitClient {
    private static Retrofit retrofit;
    public static final String BASE_URL = "http://192.168.1.27:5000/";
    public static Retrofit getRetrofitInstance() {
        if (retrofit == null) {
            retrofit = new retrofit2.Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
