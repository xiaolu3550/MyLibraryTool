package com.xiaolu.mylibrarytool;

import android.app.Application;

import com.xiaolu.mylibrary.MyLibrary;
import com.xiaolu.mylibrary.load.core.Load;
import com.xiaolu.mylibrarytool.api.RetrofitService;
import com.xiaolu.mylibrarytool.callback.EmptyCallBack;
import com.xiaolu.mylibrarytool.callback.ErrorCallBack;
import com.xiaolu.mylibrarytool.callback.LoadCallBack;
import com.xiaolu.mylibrarytool.callback.TimeOutCallBack;

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
                .init(this, false)
                .isDeBug(true)
                .mmkvInit(this, null);
        Load.beginBuilder()
                .addCallback(new LoadCallBack())
                .addCallback(new ErrorCallBack())
                .addCallback(new EmptyCallBack())
                .addCallback(new TimeOutCallBack())
                .setDefaultCallback(LoadCallBack.class)
                .commit();
    }
}
