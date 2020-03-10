package com.jundu.mylibrary;

import android.app.Application;

import com.hjq.toast.ToastUtils;
import com.jundu.mylibrary.helper.RetrofitHelper;
import com.jundu.mylibrary.net.RetrofitClient;
import com.socks.library.KLog;
import com.tamsiree.rxtool.RxAppTool;
import com.tamsiree.rxtool.RxTool;


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

    public RetrofitClient RetrofitConfige(String url) {
        return RetrofitClient.getInstance().setBaseUrl(url);
    }
}

