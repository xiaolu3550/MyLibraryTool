package com.xiaolu.mylibrary.base;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.hjq.toast.ToastUtils;
import com.xiaolu.mylibrary.R;
import com.xiaolu.mylibrary.eventbean.MessageEvent;
import com.xiaolu.mylibrary.utils.EventBusUtil;
import com.xiaolu.mylibrary.utils.ToolbarHelper;
import com.socks.library.KLog;
import com.tamsiree.rxtool.RxActivityTool;
import com.trello.rxlifecycle2.components.RxFragment;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.ButterKnife;
import butterknife.Unbinder;


abstract public class RXBaseFragment extends RxFragment {
    /**
     * 根View
     */
    public View myRootView;
    /**
     * 当做Context去使用, MainActivity
     */
    public Activity mActivity;
    /**
     * 是否对用户可见
     */
    protected boolean mIsVisible;
    /**
     * 是否加载完成
     * 当执行完onCreateView,View的初始化方法后方法后即为true
     */
    protected boolean mIsPrepare;
    /**
     * 日志输出标志
     **/
    protected final String TAG = this.getClass().getSimpleName();
    private Unbinder bind;

    //fragment创建
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        KLog.d("onCreate", TAG + "-->onCreate()");
        mActivity = getActivity();//获取fragment所依赖的activity的对象
        Bundle bundle = getArguments();
        initParams(bundle);
        if (isRegisterEventBus()) {
            KLog.d("EventBusRegister", TAG + "-->register()");
            EventBusUtil.register(this);
        }
    }


    //初始化fragment布局
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        myRootView = inflater.inflate(setLayoutResourceId(), container, false);
        KLog.d("onCreateView", TAG + "-->onCreateView()");
        Toolbar toolbar = myRootView.findViewById(R.id.toolbar);
        if (toolbar != null) {
            ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
            // 默认不显示原生标题
            ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayShowTitleEnabled(false);
            initToolbar(new ToolbarHelper(toolbar));
        }
        bind = ButterKnife.bind(this, myRootView);
        initView();
        mIsPrepare = true;
        setListener();
        return myRootView;
    }

    protected abstract void initToolbar(ToolbarHelper toolbarHelper);

    /**
     * 是否注册事件分发
     *
     * @return true绑定EventBus事件分发，默认不绑定，子类需要绑定的话复写此方法返回true.
     */
    protected boolean isRegisterEventBus() {
        return false;
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventBusCome(MessageEvent event) {
        if (event != null) {
            receiveEvent(event);
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void onStickyEventBusCome(MessageEvent event) {
        if (event != null) {
            receiveStickyEvent(event);
        }
    }

    /**
     * 接收到分发到事件
     *
     * @param event 事件
     */
    protected void receiveEvent(MessageEvent event) {

    }

    /**
     * 接受到分发的粘性事件
     *
     * @param event 粘性事件
     */
    protected void receiveStickyEvent(MessageEvent event) {

    }

    //fragment所在的activity创建完成
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        KLog.d("onActivityCreated", TAG + "-->onActivityCreated()");
//        initDate();
        doBusiness(mActivity);
    }

    protected abstract int setLayoutResourceId();

    /**
     * [初始化参数]
     *
     * @param bundle
     */
    public abstract void initParams(Bundle bundle);

    /**
     * 初始化界面
     */
    public abstract void initView();

    /**
     * [业务操作]
     *
     * @param mActivity
     */
    protected abstract void doBusiness(Activity mActivity);

    /**
     * 点击事件
     */
    public abstract void setListener();

    /**
     * 初始化数据
     */

    public void initDate() {

    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);

        this.mIsVisible = isVisibleToUser;

        if (isVisibleToUser) {
            onVisibleToUser();
        }
    }

    /**
     * 用户可见时执行的操作
     */
    protected void onVisibleToUser() {
        if (mIsPrepare && mIsVisible) {

            onLazyLoad();
            //数据加载完毕,恢复标记,防止重复加载
            mIsVisible = false;
            mIsPrepare = false;
        }
    }

    /**
     * 检查activity连接情况
     */
    public void checkActivityAttached() {
        if (getActivity() == null) {
            throw new ActivityNotAttachedException();
        }
    }

    public static class ActivityNotAttachedException extends RuntimeException {
        public ActivityNotAttachedException() {
            super("Fragment has disconnected from Activity !");
        }
    }

    /**
     * 懒加载，仅当用户可见切view初始化结束后才会执行
     */
    protected void onLazyLoad() {

    }

    /**
     * findViewById
     *
     * @param id
     * @param <T>
     * @return
     */
    @SuppressWarnings("unchecked")
    protected <T extends View> T $(int id) {
        if (myRootView == null) {
            return null;
        }
        return (T) myRootView.findViewById(id);
    }

    /**
     * [页面跳转]
     *
     * @param clz
     */
    public void startActivity(Class<?> clz) {
        startActivity(new Intent(mActivity, clz));
    }

    /**
     * 打开指定的Activity
     *
     * @param packageName 包名
     * @param className   类名
     */
    protected void launchActivity(String packageName, String className) {
        RxActivityTool.launchActivity(mActivity, packageName, className);
    }

    /**
     * 打开指定的Activity
     *
     * @param packageName 包名
     * @param className   类名
     * @param bundle
     */
    protected void launchActivity(String packageName, String className, Bundle bundle) {
        RxActivityTool.launchActivity(mActivity, packageName, className);
    }

    /**
     * 跳转到指定Activity并关闭当前Activity
     */
    protected void skipActivityAndFinish(Class clazz) {
        RxActivityTool.skipActivityAndFinish(mActivity, clazz);
    }

    /**
     * 跳转到指定Activity并关闭当前Activity
     */
    protected void skipActivityAndFinish(Class clazz, Bundle bundle) {
        RxActivityTool.skipActivityAndFinish(mActivity, clazz, bundle);
    }

    /**
     * 跳转到指定Activity
     */
    protected void skipActivity(Class clazz) {
        RxActivityTool.skipActivity(mActivity, clazz);
    }

    /**
     * 跳转到指定Activity
     */
    protected void skipActivity(Class clazz, Bundle bundle) {
        RxActivityTool.skipActivity(mActivity, clazz, bundle);
    }

    /**
     * 跳转后Finish之前所有的Activity
     */
    protected void skipActivityAndFinishAll(Class clazz) {
        RxActivityTool.skipActivityAndFinishAll(mActivity, clazz);
    }

    /**
     * 跳转后Finish之前所有的Activity
     */
    protected void skipActivityAndFinishAll(Class clazz, Bundle bundle) {
        RxActivityTool.skipActivityAndFinishAll(mActivity, clazz, bundle);
    }

    /**
     * activityForResult封装
     */
    protected void skipActivityForResult(Class clazz, int requestCode) {
        RxActivityTool.skipActivityForResult(mActivity, clazz, requestCode);
    }

    /**
     * activityForResult封装
     */
    protected void skipActivityForResult(Class clazz, Bundle bundle, int requestCode) {
        RxActivityTool.skipActivityForResult(mActivity, clazz, bundle, requestCode);
    }

    /**
     * 获取launcher activity
     *
     * @param packageName
     */
    protected void getLauncherActivity(String packageName) {
        RxActivityTool.getLauncherActivity(mActivity, packageName);
    }

    /**
     * 关闭当前Activity
     */
    protected void finishActivity() {
        RxActivityTool.finishActivity();
    }

    /**
     * 关闭所有Activity
     */
    protected void finishAllActivity() {
        RxActivityTool.finishAllActivity();
    }

    /**
     * 关闭指定Activity
     */
    protected void finishActivity(Activity activity) {
        RxActivityTool.finishActivity(activity);
    }

    /**
     * 关闭指定类名的所有Activity
     */
    protected void finishActivity(Class clz) {
        RxActivityTool.finishActivity(clz);
    }

    /**
     * 显示底部Toast
     *
     * @param msg
     */
    protected void showBottomToast(String msg) {
        ToastUtils.show(msg);
        ToastUtils.setGravity(Gravity.BOTTOM, 0, 50);
    }

    /**
     * 显示顶部Toast
     *
     * @param msg
     */
    protected void showTopToast(String msg) {
        ToastUtils.show(msg);
        ToastUtils.setGravity(Gravity.TOP, 0, -50);
    }

    /**
     * 显示居中Toast
     *
     * @param msg
     */
    protected void showCenterToast(String msg) {
        ToastUtils.show(msg);
        ToastUtils.setGravity(Gravity.CENTER, 0, 0);
    }

    /**
     * 显示自定义位置Toast
     *
     * @param gravity – 重心
     * @param xOffset – x轴偏移
     * @param yOffset – y轴偏移
     * @param msg
     */
    protected void showToast(String msg, int gravity, int xOffset, int yOffset) {
        ToastUtils.show(msg);
        ToastUtils.setGravity(gravity, xOffset, yOffset);
    }

    @Override
    public void onResume() {
        super.onResume();
        KLog.d("onResume", TAG + "-->onResume()");
    }

    @Override
    public void onStop() {
        super.onStop();
        KLog.d("onStop", TAG + "-->onStop()");
    }

    @Override
    public void onStart() {
        super.onStart();
        KLog.d("onStart", TAG + "-->onStart()");
    }

    @Override
    public void onPause() {
        super.onPause();
        KLog.d("onPause", TAG + "-->onPause()");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        //页面销毁,恢复标记
        KLog.d("onDestroyView", TAG + "-->onDestroyView()");
        bind.unbind();
        mIsPrepare = false;
        mIsVisible = false;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        KLog.d("onDestroy", TAG + "-->onDestroy()");
        if (isRegisterEventBus()) {
            KLog.d("EventBusUnRegister", TAG + "-->unregister()");
            EventBusUtil.unregister(this);
        }
    }
}
