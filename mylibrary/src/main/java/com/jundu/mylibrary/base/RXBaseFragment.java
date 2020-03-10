package com.jundu.mylibrary.base;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.jundu.mylibrary.R;
import com.jundu.mylibrary.eventbean.MessageEvent;
import com.jundu.mylibrary.utils.EventBusUtil;
import com.jundu.mylibrary.utils.ToolbarHelper;
import com.socks.library.KLog;
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
        mActivity = getActivity();//获取fragment所依赖的activity的对象
        Bundle bundle = getArguments();
        initParams(bundle);
        if (isRegisterEventBus()) {
            KLog.d();
            EventBusUtil.register(this);
        }
    }


    //初始化fragment布局
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        myRootView = inflater.inflate(setLayoutResourceId(), container, false);
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
     * [携带数据的页面跳转]
     *
     * @param clz
     * @param bundle
     */
    public void startActivity(Class<?> clz, Bundle bundle) {
        Intent intent = new Intent();
        intent.setClass(mActivity, clz);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }

    /**
     * [含有Bundle通过Class打开编辑界面]
     *
     * @param cls
     * @param bundle
     * @param requestCode
     */
    public void startActivityForResult(Class<?> cls, Bundle bundle,
                                       int requestCode) {
        Intent intent = new Intent();
        intent.setClass(mActivity, cls);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivityForResult(intent, requestCode);
    }

    protected void showToast(String msg) {
        Toast.makeText(mActivity, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        //页面销毁,恢复标记
        bind.unbind();
        mIsPrepare = false;
        mIsVisible = false;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (isRegisterEventBus()) {
            EventBusUtil.unregister(this);
        }
    }

}
