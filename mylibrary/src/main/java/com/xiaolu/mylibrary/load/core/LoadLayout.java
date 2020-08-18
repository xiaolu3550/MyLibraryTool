package com.xiaolu.mylibrary.load.core;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;

import com.xiaolu.mylibrary.load.LoadUtil;
import com.xiaolu.mylibrary.load.callback.BaseCallBack;
import com.xiaolu.mylibrary.load.callback.SuccessCallBack;

import java.util.HashMap;
import java.util.Map;

/**
 * ================================================
 *
 * @ProjectName: MyLibraryTool
 * @Package: com.xiaolu.mylibrary.load.core
 * @ClassName: LoadLayout
 * @Description: java类作用描述
 * @Author: xiaol
 * @CreateDate: 2020/8/18 11:17
 * @UpdateUser: xiaol
 * @UpdateDate: 2020/8/18 11:17
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 * ================================================
 */
public class LoadLayout extends FrameLayout {
    private final String TAG = getClass().getSimpleName();
    private Map<Class<? extends BaseCallBack>, BaseCallBack> callbacks = new HashMap<>();
    private Context context;
    private BaseCallBack.OnReloadListener onReloadListener;
    private Class<? extends BaseCallBack> preCallback;
    private Class<? extends BaseCallBack> curCallback;
    private static final int CALLBACK_CUSTOM_INDEX = 1;

    public LoadLayout(@NonNull Context context) {
        super(context);
    }

    public LoadLayout(@NonNull Context context, BaseCallBack.OnReloadListener onReloadListener) {
        this(context);
        this.context = context;
        this.onReloadListener = onReloadListener;
    }

    public void setupSuccessLayout(BaseCallBack callback) {
        addCallback(callback);
        View successView = callback.getRootView();
        successView.setVisibility(View.INVISIBLE);
        addView(successView, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT));
        curCallback = SuccessCallBack.class;
    }

    public void setupCallback(BaseCallBack callback) {
        BaseCallBack cloneCallback = callback.copy();
        cloneCallback.setCallBack(context, onReloadListener);
        addCallback(cloneCallback);
    }

    public void addCallback(BaseCallBack callback) {
        if (!callbacks.containsKey(callback.getClass())) {
            callbacks.put(callback.getClass(), callback);
        }
    }

    public void showCallback(final Class<? extends BaseCallBack> callback) {
        checkCallbackExist(callback);
        if (LoadUtil.isMainThread()) {
            showCallbackView(callback);
        } else {
            postToMainThread(callback);
        }
    }

    public Class<? extends BaseCallBack> getCurrentCallback() {
        return curCallback;
    }

    private void postToMainThread(final Class<? extends BaseCallBack> status) {
        post(new Runnable() {
            @Override
            public void run() {
                showCallbackView(status);
            }
        });
    }

    private void showCallbackView(Class<? extends BaseCallBack> status) {
        if (preCallback != null) {
            if (preCallback == status) {
                return;
            }
            callbacks.get(preCallback).onDetach();
        }
        if (getChildCount() > 1) {
            removeViewAt(CALLBACK_CUSTOM_INDEX);
        }
        for (Class key : callbacks.keySet()) {
            if (key == status) {
                SuccessCallBack successCallback = (SuccessCallBack) callbacks.get(SuccessCallBack.class);
                if (key == SuccessCallBack.class) {
                    successCallback.show();
                } else {
                    successCallback.showWithCallback(callbacks.get(key).getSuccessVisible());
                    View rootView = callbacks.get(key).getRootView();
                    addView(rootView);
                    callbacks.get(key).onAttach(context, rootView);
                }
                preCallback = status;
            }
        }
        curCallback = status;
    }

    public void setCallBack(Class<? extends BaseCallBack> callback, Transport transport) {
        if (transport == null) {
            return;
        }
        checkCallbackExist(callback);
        transport.order(context, callbacks.get(callback).obtainRootView());
    }

    private void checkCallbackExist(Class<? extends BaseCallBack> callback) {
        if (!callbacks.containsKey(callback)) {
            throw new IllegalArgumentException(String.format("The Callback (%s) is nonexistent.", callback
                    .getSimpleName()));
        }
    }
}
