package com.xiaolu.mylibrary.base;

import android.app.Application;

public class BaseApplication extends Application {
    private static Application instance;

    public static Application getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }
}
