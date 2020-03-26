package com.xiaolu.mylibrary.base;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;

import androidx.appcompat.widget.Toolbar;

import com.hjq.toast.ToastUtils;
import com.jaeger.library.StatusBarUtil;
import com.xiaolu.mylibrary.R;
import com.xiaolu.mylibrary.eventbean.MessageEvent;
import com.xiaolu.mylibrary.utils.EventBusUtil;
import com.xiaolu.mylibrary.utils.ToolbarHelper;
import com.socks.library.KLog;
import com.tamsiree.rxtool.RxActivityTool;
import com.tamsiree.rxtool.RxBarTool;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.Stack;

import butterknife.ButterKnife;


public abstract class RXBaseActivity extends RxAppCompatActivity implements View.OnClickListener {
    /**
     * 是否沉浸状态栏
     **/
    private boolean isSetStatusBar = false;
    private boolean isStatusBar = true;
    /**
     * 是否允许全屏
     **/
    private boolean mAllowFullScreen = false;
    /**
     * 是否禁止旋转屏幕
     **/
    private boolean isAllowScreenRoate = false;
    /**
     * 当前Activity渲染的视图View
     **/
    private View mContextView = null;
    /**
     * 是否强制横屏
     */
    private boolean isPortarait = false;
    /**
     * 日志输出标志
     **/
    protected final String TAG = this.getClass().getSimpleName();

    public Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        KLog.d("onCreate", TAG + "-->onCreate()");
        mContext = this;
        setWindow(getWindow());
        RxActivityTool.addActivity(this);
        Bundle bundle = getIntent().getExtras();
        View mView = bindView();
        if (mAllowFullScreen) {
            RxBarTool.noTitle(this);
        }
        initParms(bundle);
        if (null == mView) {
            mContextView = LayoutInflater.from(this).inflate(bindLayout(), null);
        } else {
            mContextView = mView;
        }
        Toolbar toolbar = mContextView.findViewById(R.id.toolbar);
        if (toolbar != null) {
            setSupportActionBar(toolbar);
            // 默认不显示原生标题
            getSupportActionBar().setDisplayShowTitleEnabled(false);
            initToolbar(new ToolbarHelper(toolbar));
        }
        if (isSetStatusBar) {
            steepStatusBar();
        }
        setContentView(mContextView);
        ButterKnife.bind(this);
        if (!isAllowScreenRoate) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }
        if (isPortarait) {
            isPortarait();
        }
        StatusBarUtil.setColor(this, getResources().getColor(R.color.white));
        initView(mContextView);
        doBusiness(this);
        setListener();
        if (isRegisterEventBus()) {
            KLog.d(TAG, "EventBusUtil --> register");
            EventBusUtil.register(this);
        }
    }

    /**
     * 设置Activity的状态
     *
     * @param window
     */
    protected void setWindow(Window window) {

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

    /**
     * 重写 getResource 方法，防止系统字体影响
     */
    @Override
    public Resources getResources() {//禁止app字体大小跟随系统字体大小调节
        Resources resources = super.getResources();
        if (resources != null && resources.getConfiguration().fontScale != 1.0f) {
            Configuration configuration = resources.getConfiguration();
            configuration.fontScale = 1.0f;
            resources.updateConfiguration(configuration, resources.getDisplayMetrics());
        }
        return resources;
    }

    /**
     * [初始化参数]
     *
     * @param parms
     */
    public abstract void initParms(Bundle parms);


    /**
     * [绑定视图]
     *
     * @return
     */
    public abstract View bindView();

    /**
     * [绑定布局]
     *
     * @return
     */
    public abstract int bindLayout();

    /**
     * [初始化控件]
     *
     * @param view
     */
    public abstract void initView(final View view);

    /**
     * [绑定控件]
     *
     * @param resId
     * @return
     */
    protected <T extends View> T $(int resId) {
        return super.findViewById(resId);
    }

    /**
     * [设置监听]
     */
    public abstract void setListener();

    /**
     * View点击
     **/
    public abstract void widgetClick(View v);

    /**
     * 加载网络数据
     * 子类需要可以重写
     */
    public void initDate() {
    }

    public void initDate(String url) {
    }

    @Override
    public void onClick(View v) {
       /* if (RxTool.isFastClick(1000))
            return;*/
        widgetClick(v);
    }


    /**
     * [业务操作]
     *
     * @param mContext
     */
    public abstract void doBusiness(Context mContext);


    @Override
    protected void onRestart() {
        super.onRestart();
        KLog.d("onRestart", TAG + "-->onRestart()");
    }

    @Override
    protected void onStart() {
        super.onStart();
        KLog.d("onStart", TAG + "-->onStart()");
    }

    @Override
    protected void onResume() {
        super.onResume();
        //  doBusiness(this);
        KLog.d("onResume", TAG + "-->onResume()");
    }

    @Override
    protected void onPause() {
        super.onPause();
        KLog.d("onPause", TAG + "-->onPause()");
    }

    @Override
    protected void onStop() {
        super.onStop();
        KLog.d("onStop", TAG + "-->onStop()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        KLog.d("onDestroy", TAG + "-->onDestroy()");
        if (isRegisterEventBus()) {
            KLog.d(TAG, "EventBusUtil-->unregister");
            EventBusUtil.unregister(this);
        }
    }

    public boolean isStatusBar() {
        return isStatusBar;
    }

    public void setStatusBar(boolean statusBar) {
        isStatusBar = statusBar;
    }

    /**
     * [沉浸状态栏]
     */
    private void steepStatusBar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            RxBarTool.setTransparentStatusBar(this);
           /* // 透明状态栏
            getWindow().addFlags(
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            // 透明导航栏
            getWindow().addFlags(
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);*/
        }
    }

    /**
     * [是否设置沉浸状态栏]
     * 默认为否
     *
     * @param isSetStatusBar
     */
    public void setSteepStatusBar(boolean isSetStatusBar) {
        this.isSetStatusBar = isSetStatusBar;
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

    /**
     * 获取Activity栈
     */
    protected Stack<Activity> getActivityStack() {
        return RxActivityTool.getActivityStack();
    }

    /**
     * 判断是否存在指定Activity
     *
     * @param packageName 包名
     * @param className   类名
     */
    protected void isExistActivity(String packageName, String className) {
        RxActivityTool.isExistActivity(mContext, packageName, className);
    }

    /**
     * 打开指定的Activity
     *
     * @param packageName 包名
     * @param className   类名
     */
    protected void launchActivity(String packageName, String className) {
        RxActivityTool.launchActivity(mContext, packageName, className);
    }

    /**
     * 打开指定的Activity
     *
     * @param packageName 包名
     * @param className   类名
     * @param bundle
     */
    protected void launchActivity(String packageName, String className, Bundle bundle) {
        RxActivityTool.launchActivity(mContext, packageName, className);
    }

    /**
     * 跳转到指定Activity并关闭当前Activity
     */
    protected void skipActivityAndFinish(Class clazz) {
        RxActivityTool.skipActivityAndFinish(mContext, clazz);
    }

    /**
     * 跳转到指定Activity并关闭当前Activity
     */
    protected void skipActivityAndFinish(Class clazz, Bundle bundle) {
        RxActivityTool.skipActivityAndFinish(mContext, clazz, bundle);
    }

    /**
     * 跳转到指定Activity
     */
    protected void skipActivity(Class clazz) {
        RxActivityTool.skipActivity(mContext, clazz);
    }

    /**
     * 跳转到指定Activity
     */
    protected void skipActivity(Class clazz, Bundle bundle) {
        RxActivityTool.skipActivity(mContext, clazz, bundle);
    }

    /**
     * 跳转后Finish之前所有的Activity
     */
    protected void skipActivityAndFinishAll(Class clazz) {
        RxActivityTool.skipActivityAndFinishAll(mContext, clazz);
    }

    /**
     * 跳转后Finish之前所有的Activity
     */
    protected void skipActivityAndFinishAll(Class clazz, Bundle bundle) {
        RxActivityTool.skipActivityAndFinishAll(mContext, clazz, bundle);
    }

    /**
     * activityForResult封装
     */
    protected void skipActivityForResult(Class clazz, int requestCode) {
        RxActivityTool.skipActivityForResult(this, clazz, requestCode);
    }

    /**
     * activityForResult封装
     */
    protected void skipActivityForResult(Class clazz, Bundle bundle, int requestCode) {
        RxActivityTool.skipActivityForResult(this, clazz, bundle, requestCode);
    }

    /**
     * 获取launcher activity
     *
     * @param packageName
     */
    protected void getLauncherActivity(String packageName) {
        RxActivityTool.getLauncherActivity(mContext, packageName);
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
     * 退出App
     */
    protected void exitApp() {
        RxActivityTool.AppExit(mContext);
    }


    /**
     * [是否允许全屏]
     * 默认为是
     *
     * @param allowFullScreen
     */
    public void setAllowFullScreen(boolean allowFullScreen) {
        this.mAllowFullScreen = allowFullScreen;
    }

    /**
     * [是否允许屏幕旋转]
     * 默认为否
     *
     * @param isAllowScreenRoate
     */
    public void setScreenRoate(boolean isAllowScreenRoate) {
        this.isAllowScreenRoate = isAllowScreenRoate;
    }

    /**
     * 判断是否是为横屏
     * ORIENTATION_LANDSCAPE 横屏
     * ORIENTATION_PORTRAIT 竖屏
     */
    private void isPortarait() {
        Configuration mConfiguration = this.getResources().getConfiguration(); //获取设置的配置信息
        int ori = mConfiguration.orientation; //获取屏幕方向
        if (ori == mConfiguration.ORIENTATION_LANDSCAPE) {
            hideBottomUIMenu();
        } else if (ori == mConfiguration.ORIENTATION_PORTRAIT) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);//强制为横屏
        }
    }

    /**
     * 隐藏菜单虚拟按键
     */
    protected void hideBottomUIMenu() {
        //隐藏虚拟按键，并且全屏
        if (Build.VERSION.SDK_INT > 11 && Build.VERSION.SDK_INT < 19) { // lower api
            View v = this.getWindow().getDecorView();
            v.setSystemUiVisibility(View.GONE);
        } else if (Build.VERSION.SDK_INT >= 19) {
            //for new api versions.
            View decorView = getWindow().getDecorView();
            int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY | View.SYSTEM_UI_FLAG_FULLSCREEN;
            decorView.setSystemUiVisibility(uiOptions);
        }
    }
}
