package com.esyto.myprostruct;

import android.app.Application;
import android.content.Context;
import android.content.res.Resources;

import com.esyto.myprostruct.api.DataCache;
import com.esyto.myprostruct.util.SpUtil;


/**
 * Created by baixiaokang on 16/4/23.
 */
public class App extends Application {
    private static App mApp;
    public static DataCache mDataCache;

    @Override
    public void onCreate() {
        super.onCreate();
        mApp = this;
        SpUtil.init(this);
        mDataCache = new DataCache();
    }

    public static Context getAppContext() {
        return mApp;
    }

    public static Resources getAppResources() {
        return mApp.getResources();
    }

}
