package com.xiaolu.mylibrary.load.core;

import android.content.Context;
import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.xiaolu.mylibrary.load.callback.BaseCallBack;
import com.xiaolu.mylibrary.load.callback.SuccessCallBack;

import java.util.List;

/**
 * ================================================
 *
 * @ProjectName: MyLibraryTool
 * @Package: com.xiaolu.mylibrary.load.core
 * @ClassName: LoadService
 * @Description: java类作用描述
 * @Author: xiaol
 * @CreateDate: 2020/8/18 11:19
 * @UpdateUser: xiaol
 * @UpdateDate: 2020/8/18 11:19
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 * ================================================
 */
public class LoadService<T> {
    private final String TAG = getClass().getSimpleName();
    private LoadLayout loadLayout;
    private Convertor<T> convertor;

    LoadService(Convertor<T> convertor, LoadLayout loadLayout, Load.Builder builder) {
        this.convertor = convertor;
        this.loadLayout = loadLayout;
        initCallback(builder);
    }

    private void initCallback(Load.Builder builder) {
        List<BaseCallBack> callbacks = builder.getCallbacks();
        final Class<? extends BaseCallBack> defalutCallback = builder.getDefaultCallback();
        if (callbacks != null && callbacks.size() > 0) {
            for (BaseCallBack callback : callbacks) {
                loadLayout.setupCallback(callback);
            }
        }
        new Handler().post(new Runnable() {
            @Override
            public void run() {
                if (defalutCallback != null) {
                    loadLayout.showCallback(defalutCallback);
                }
            }
        });

    }

    public void showSuccess() {
        loadLayout.showCallback(SuccessCallBack.class);
    }

    public void showCallback(Class<? extends BaseCallBack> callback) {
        loadLayout.showCallback(callback);
    }

    public void showWithConvertor(T t) {
        if (convertor == null) {
            throw new IllegalArgumentException("You haven't set the Convertor.");
        }
        loadLayout.showCallback(convertor.map(t));
    }

    public LoadLayout getLoadLayout() {
        return loadLayout;
    }

    public Class<? extends BaseCallBack> getCurrentCallback() {
        return loadLayout.getCurrentCallback();
    }

    /**
     * 如果希望将工具栏保留在Fragment中，请获取rootView
     *
     * @deprecated
     */
    public LinearLayout getTitleLoadLayout(Context context, ViewGroup rootView, View titleView) {
        LinearLayout newRootView = new LinearLayout(context);
        newRootView.setOrientation(LinearLayout.VERTICAL);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);
        newRootView.setLayoutParams(layoutParams);
        rootView.removeView(titleView);
        newRootView.addView(titleView);
        newRootView.addView(loadLayout, layoutParams);
        return newRootView;
    }

    /**
     * 动态修改回调
     *
     * @param callback  您要修改哪个回调（布局，事件）
     * @param transport 接口包含修改逻辑
     */
    public LoadService<T> setCallBack(Class<? extends BaseCallBack> callback, Transport transport) {
        loadLayout.setCallBack(callback, transport);
        return this;
    }
}
