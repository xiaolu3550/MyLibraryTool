package com.xiaolu.mylibrary.base;


import android.os.Bundle;

import android.util.SparseArray;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.IdRes;
import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;
import androidx.annotation.StyleRes;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

/**
 * @ClassName: BaseDialog
 * @Description: : (dialog 基类)
 */
public abstract class BaseDialog extends DialogFragment {

    public enum DialogType {
        mid, bottom, top, full, right
    }

    protected View mRootView;
    private SparseArray<View> mViews;
    Window window;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NORMAL, getDialogStyle());
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        if (mRootView == null) {
            mRootView = inflater.inflate(getDialogLayout(), container);
            mViews = new SparseArray<>();
            window = getDialog().getWindow();
        } else {
            ViewGroup parent = (ViewGroup) mRootView.getParent();
            if (parent != null) {
                parent.removeView(mRootView);
            }
        }
        return mRootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        bindView(savedInstanceState);
    }

    /**
     * 绑定视图
     *
     * @param bundle savedInstanceState
     */
    protected void bindView(@Nullable Bundle bundle) {
        build(DialogType.mid);
        this.getDialog().setCanceledOnTouchOutside(false);
    }

    /**
     * 再次根据选择的类型进行布局设置
     *
     * @param type 风格
     * @return dialog
     */
    protected BaseDialog build(DialogType type) {
        switch (type) {
            case mid:
                break;
            case bottom:
                initBottomDialog();
                break;
            case top:
                break;
            case full:
                break;
            case right:
                initRightiDialog();
                break;
            default:
                break;
        }
        return this;
    }

    protected void initBottomDialog() {
        if (null != window) {
            window.getDecorView().setPadding(0, 0, 0, 0); //消除边距
            WindowManager.LayoutParams lp = window.getAttributes();
            lp.width = WindowManager.LayoutParams.MATCH_PARENT;
            lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
            //设置dialog的位置在底部
            lp.gravity = Gravity.BOTTOM;
            window.setAttributes(lp);
            window.setGravity(Gravity.BOTTOM);
        }
    }

    protected void initRightiDialog() {
        if (null != window) {
            window.getDecorView().setPadding(0, 0, 0, 0); //消除边距
            WindowManager.LayoutParams lp = window.getAttributes();
            lp.width = WindowManager.LayoutParams.WRAP_CONTENT;
            lp.height = WindowManager.LayoutParams.MATCH_PARENT;
            //设置dialog的位置在左侧
            lp.gravity = Gravity.RIGHT;
            window.setAttributes(lp);
            window.setGravity(Gravity.RIGHT);
        }
    }

    public void setAnimations(@StyleRes int animationId) {
        if (null != window)
            window.setWindowAnimations(animationId);
    }

    public void show(FragmentActivity activity) {
        if (null != this.getDialog() && this.getDialog().isShowing()) {
            return;
        }
        //        show(activity.getSupportFragmentManager(), getDialogTag());
        show(activity.getSupportFragmentManager());
    }

    public void show(Fragment fragment) {
        if (null != this.getDialog() && this.getDialog().isShowing()) {
            return;
        }
        //        show(fragment.getFragmentManager(), getDialogTag());
        show(fragment.getFragmentManager());
    }

    public void show(FragmentManager fm) {
        //这里直接调用show方法会报java.lang.IllegalStateException: Can not perform this action after onSaveInstanceState
        //因为show方法中是通过commit进行的提交(通过查看源码)
        //这里为了修复这个问题，使用commitAllowingStateLoss()方法
        //注意：DialogFragment是继承自android.app.Fragment，这里要注意同v4包中的Fragment区分，别调用串了
        //DialogFragment有自己的好处，可能也会带来别的问题
        //dialog.show(getFragmentManager(), "SignDialog");
        FragmentTransaction ft = fm.beginTransaction();
        ft.add(this, getDialogTag());
        ft.commitAllowingStateLoss();
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        this.getDialog().setCanceledOnTouchOutside(isCancelableOnTouchOutside());
        this.getDialog().setCancelable(isCancelable());
    }

    protected boolean isCancelableOnTouchOutside() {
        return true;
    }

    @Override
    public void dismiss() {
        if (null == this.getDialog() || !this.getDialog().isShowing()) {
            return;
        }
        super.dismiss();
    }

    protected abstract String getDialogTag(); //获取dialog的标签

    @LayoutRes
    protected abstract int getDialogLayout(); //获取dialog的布局

    @StyleRes
    protected abstract int getDialogStyle(); //获取dialog的风格

    /**
     * fragment中可以通过这个方法直接找到需要的view，而不需要进行类型强转
     *
     * @param viewId view 资源ID
     * @param <E>    view 类型
     * @return view
     */
    //@Deprecated
    protected <E extends View> E findViewById(@IdRes int viewId) {
        if (mRootView != null) {
            E view = (E) mViews.get(viewId);
            if (view == null) {
                view = (E) mRootView.findViewById(viewId);
                mViews.put(viewId, view);
            }
            return view;
        }
        return null;
    }

}
