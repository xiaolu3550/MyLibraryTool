package com.xiaolu.mylibrary.load.callback;

import android.content.Context;
import android.view.View;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * ================================================
 *
 * @ProjectName: MyLibraryTool
 * @Package: com.xiaolu.mylibrary.load.callback
 * @ClassName: BaseCallBack
 * @Description: java类作用描述
 * @Author: xiaol
 * @CreateDate: 2020/8/18 11:10
 * @UpdateUser: xiaol
 * @UpdateDate: 2020/8/18 11:10
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 * ================================================
 */
public abstract class BaseCallBack implements Serializable {

    private View rootView;
    private Context context;
    private OnReloadListener onReloadListener;
    private boolean successViewVisible;

    public BaseCallBack() {
    }

    BaseCallBack(View view, Context context, OnReloadListener onReloadListener) {
        this.rootView = view;
        this.context = context;
        this.onReloadListener = onReloadListener;
    }

    public BaseCallBack setCallBack(Context context, OnReloadListener onReloadListener) {
        this.context = context;
        this.onReloadListener = onReloadListener;
        return this;
    }

    public View getRootView() {
        int resId = onCreateView();
        if (resId == 0 && rootView != null) {
            return rootView;
        }

        if (onBuildView(context) != null) {
            rootView = onBuildView(context);
        }

        if (rootView == null) {
            rootView = View.inflate(context, onCreateView(), null);
        }
        rootView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onReloadEvent(context, rootView)) {
                    return;
                }
                if (onReloadListener != null) {
                    onReloadListener.onReload(v);
                }
            }
        });
        onViewCreate(context, rootView);
        return rootView;
    }

    protected View onBuildView(Context context) {
        return null;
    }

    /**
     * 如果返回true，则在附加回调视图时将显示successView。
     */
    public boolean getSuccessVisible() {
        return successViewVisible;
    }

    void setSuccessVisible(boolean visible) {
        this.successViewVisible = visible;
    }

    /**
     * @deprecated Use {@link #onReloadEvent(Context context, View view)} instead.
     */
    protected boolean onRetry(Context context, View view) {
        return false;
    }

    protected boolean onReloadEvent(Context context, View view) {
        return false;
    }

    public BaseCallBack copy() {
        ByteArrayOutputStream bao = new ByteArrayOutputStream();
        ObjectOutputStream oos;
        Object obj = null;
        try {
            oos = new ObjectOutputStream(bao);
            oos.writeObject(this);
            oos.close();
            ByteArrayInputStream bis = new ByteArrayInputStream(bao.toByteArray());
            ObjectInputStream ois = new ObjectInputStream(bis);
            obj = ois.readObject();
            ois.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return (BaseCallBack) obj;
    }


    public View obtainRootView() {
        if (rootView == null) {
            rootView = View.inflate(context, onCreateView(), null);
        }
        return rootView;
    }

    public interface OnReloadListener extends Serializable {
        void onReload(View v);
    }

    protected abstract int onCreateView();

    /**
     * 之后立即致电 {@link #onCreateView()}
     *
     */
    protected void onViewCreate(Context context, View view) {
    }

    /**
     * 当BaseCallBack的rootView附加到其LoadLayout时调用。
     *
     */
    public void onAttach(Context context, View view) {
    }

    /**
     * 从其LoadLayout中删除BaseCallBack的rootView时调用。
     *
     */
    public void onDetach() {
    }

}
