package com.example.cynthia.zscy.Utils;

import android.content.Context;

import com.example.cynthia.zscy.Bean.User;

public class Application extends android.app.Application {
    private static Context mContext;
    private static String ac;
    private static String pw;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
    }

    public static void setPw(String passw) {
        pw = passw;
    }

    public static String getPw() {
        return pw;
    }

    public static String getAc() {
        return ac;
    }

    public static void setAc(String acc){
        ac = acc;
    }

    public static Context getContext() {
        return mContext;
    }
}
