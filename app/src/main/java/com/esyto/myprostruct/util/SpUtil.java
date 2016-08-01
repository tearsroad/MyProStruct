package com.esyto.myprostruct.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.esyto.myprostruct.bean._User;
import com.google.gson.Gson;
import com.orhanobut.logger.Logger;

/**
 * Created by Administrator on 2016/4/5.
 */
public class SpUtil {
    static SharedPreferences prefs;

    public static void init(Context context) {
        prefs = PreferenceManager.getDefaultSharedPreferences(context);
    }

    public static boolean isNight() {
        return prefs.getBoolean("isNight", false);
    }

    public static void setNight(Context context, boolean isNight) {
        prefs.edit().putBoolean("isNight", isNight).commit();
//        if (context instanceof BaseActivity)
//            ((BaseActivity) context).reload();
    }

    public static _User getUser() {
        String user = prefs.getString("user", "");
        Logger.i("getUser:"+user);
        return new Gson().fromJson(user, _User.class);
    }

    public static void setUser(_User user) {
        Logger.i("setUser:"+new Gson().toJson(user));
        prefs.edit().putString("user", new Gson().toJson(user)).commit();
    }
}
