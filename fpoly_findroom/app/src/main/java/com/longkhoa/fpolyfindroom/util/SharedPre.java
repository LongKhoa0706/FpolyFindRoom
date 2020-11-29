package com.longkhoa.fpolyfindroom.util;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPre {
    private static SharedPre store;
    private SharedPreferences SP;
    private static String filename="Keys";
    public static int theme=1;

    private SharedPre(Context context) {
        SP = context.getApplicationContext().getSharedPreferences(filename,0);
    }

    public static SharedPre getInstance(Context context) {
        if (store == null) {
            store = new SharedPre(context);
        }
        return store;
    }

    public void put(String key, String value) {
        SharedPreferences.Editor editor;
        editor = SP.edit();
        editor.putString(key, value);
        editor.apply();
    }

    public String get(String key) {
        return SP.getString(key, null);
    }

    public int getInt(String key) {
        return SP.getInt(key, 0);
    }

    public void putInt(String key, int num) {
        SharedPreferences.Editor editor;
        editor = SP.edit();

        editor.putInt(key, num);
        editor.apply();
    }

    public void clear(){
        SharedPreferences.Editor editor;
        editor = SP.edit();

        editor.clear();
        editor.apply();
    }

    public void remove(){
        SharedPreferences.Editor editor;
        editor = SP.edit();

        editor.remove(filename);
        editor.apply();
    }

}
