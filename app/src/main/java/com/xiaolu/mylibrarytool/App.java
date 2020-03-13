package com.xiaolu.mylibrarytool;

import android.app.Application;

import com.jundu.mylibrary.MyLibrary;
import com.xiaolu.mylibrarytool.api.RetrofitService;

public class App extends Application {
    private static Application instance;

    public static Application getInstance() {
        return instance;
    }

    public static RetrofitService getClient() {
        return MyLibrary.getInstance()
                .RetrofitConfig("http://49.4.15.51:8088/vehicleLife/")
                .getApi(RetrofitService.class);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        MyLibrary.getInstance()
                .init(this)
                .isDeBug(true);
    }
}
