package com.xiaolu.mylibrarytool;

import com.xiaolu.mylibrary.MyLibrary;
import com.xiaolu.mylibrary.base.BaseApplication;
import com.xiaolu.mylibrary.load.core.Load;
import com.xiaolu.mylibrarytool.api.RetrofitService;
import com.xiaolu.mylibrarytool.callback.EmptyCallBack;
import com.xiaolu.mylibrarytool.callback.ErrorCallBack;
import com.xiaolu.mylibrarytool.callback.LoadCallBack;
import com.xiaolu.mylibrarytool.callback.TimeOutCallBack;

public class App extends BaseApplication {
    public static RetrofitService getClient() {
        return MyLibrary.getInstance()
                .RetrofitConfig("http://146.56.197.42:12002/root/")
                .getApi(RetrofitService.class);
    }

    @Override
    public void onCreate() {
        super.onCreate();
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
