package com.longkhoa.fpolyfindroom.networking;

import android.content.Context;
import android.content.SharedPreferences;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class TokenManager {


    public static final String AUTH_URL = "http://172.16.4.236:8080/";
    public static final String TOKEN = getToken();

    /**
     * Get Json Web Token (JWT)
     *
     * @POST http://localhost:8080/RestfulWebServiceExample/rest/auth
     */
    private static String getToken() {
        RequestBody requestBody = new FormBody.Builder()
                .add("username", "gpcoder")
                .add("password", "gpcoder")
                .build();

        Request request = new Request.Builder()
                .url(AUTH_URL)
                .post(requestBody)
                .build();

        OkHttpClient client = new OkHttpClient();
        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Unexpected code: " + response);
            }
            String token = response.body().string();
            System.out.println("Token: " + token);
            return token;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

//    private SharedPreferences sp;
//    private SharedPreferences.Editor spe;
//    Context context;
//
//    public TokenManager (Context cntx) {
//        // TODO Auto-generated constructor stub
//        this.context = cntx;
//        sp = context.getSharedPreferences("MyData", 0);
//        spe = sp.edit();
//    }
//
//    public void setJwtToken(String token) {
//
//        spe.putString("token", token).apply();
//    }
//
//    public String getJwtToken() {
//        String token = sp.getString("token", "");
//        if (token == null || token.isEmpty()) {
//            token = "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJleHAiOjIxNzc0NTI3OTksImlhdCI6MTUxNjAyMjk5OSwiaXNzIjoiQmFzb2JhYXMgTmVwYWwiLCJuYmYiOjE1MTYwMjI5OTksImp0aSI6Ikd1ZXN0VG9rZW4iLCJzdWIiOjB9.QikmNgBYmqch5HREGFEpUs4Xk3x-zFfDg5mhYJO7jM8";
//        }
//        return token;
//    }
}
