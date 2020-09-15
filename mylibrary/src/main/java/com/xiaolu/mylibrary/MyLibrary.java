package com.xiaolu.mylibrary;

import android.app.Application;

import com.hjq.toast.ToastUtils;
import com.tamsiree.rxkit.RxAppTool;
import com.tamsiree.rxkit.RxTool;
import com.xiaolu.mylibrary.net.RetrofitClient;
import com.socks.library.KLog;



public class MyLibrary {
    private static MyLibrary myLibrary;
    private static Application application;

    public synchronized static MyLibrary getInstance() {
        if (myLibrary == null) {
            myLibrary = new MyLibrary();
        }
        return myLibrary;
    }

    public MyLibrary init(Application context) {
        application = context;
        ToastUtils.init(context);
        RxTool.init(context);
        return this;
    }

    public MyLibrary isDeBug(boolean isDebug) {
        KLog.init(isDebug, RxAppTool.getAppName(application));
        return this;
    }

    public RetrofitClient RetrofitConfig(String url) {
        return RetrofitClient.getInstance().setBaseUrl(url);
    }
}

