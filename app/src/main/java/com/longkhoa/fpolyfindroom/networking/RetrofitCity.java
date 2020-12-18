package com.longkhoa.fpolyfindroom.networking;


import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitCity {
//    private static Context context;

    private static Retrofit retrofit;
    public static final String BASE_URL = "http://192.168.1.6:8080/";
    public static Retrofit getRetrofitInstance() {
        if (retrofit == null) {
            OkHttpClient okHttpClient = new OkHttpClient.Builder()
                    .addInterceptor(new LoggingInterceptor())
                    .addInterceptor(new AuthInterceptor())
                    .addNetworkInterceptor( new LoggingInterceptor())
                    .build();
            retrofit = new Retrofit.Builder()
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
