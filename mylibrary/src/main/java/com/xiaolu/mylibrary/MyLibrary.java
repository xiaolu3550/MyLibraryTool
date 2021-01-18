package com.xiaolu.mylibrary;

import android.app.Application;
import android.content.Context;

import com.hjq.toast.ToastUtils;
import com.tamsiree.rxkit.RxAppTool;
import com.tamsiree.rxkit.RxTool;
import com.tencent.mmkv.MMKV;
import com.xiaolu.mylibrary.log.LogUtil;
import com.xiaolu.mylibrary.net.RetrofitClient;


public class MyLibrary {
    private static MyLibrary myLibrary;
    private static Application application;

    public synchronized static MyLibrary getInstance() {
        if (myLibrary == null) {
            myLibrary = new MyLibrary();
        }
        return myLibrary;
    }

    public MyLibrary init(Application context, boolean enabled) {
        application = context;
        ToastUtils.init(context);
        RxTool.init(context)
                //以下为崩溃配置
                .crashProfile()
                //default: true
                .enabled(enabled)
                .apply();
        return this;
    }

    public MyLibrary isDeBug(boolean isDebug) {
        LogUtil.init(isDebug, RxAppTool.getAppName(application));
        return this;
    }

    public RetrofitClient RetrofitConfig(String url) {
        return RetrofitClient.getInstance().setBaseUrl(url);
    }

    /**
     * 初始化MMKV
     *
     * @param context 上下文
     * @param path    mmkv 存储路径(可以为null)
     * @return
     */
    public MyLibrary mmkvInit(Context context, String path) {
        if (path == null) {
            MMKV.initialize(context);
        } else {
            MMKV.initialize(path);
        }
        return this;
    }
}

