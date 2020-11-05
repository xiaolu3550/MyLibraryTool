package com.xiaolu.mylibrarykotlin.base

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.pm.ActivityInfo
import android.content.res.Configuration
import android.content.res.Resources
import android.os.Build
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.Window
import androidx.appcompat.widget.Toolbar
import butterknife.ButterKnife
import com.gyf.immersionbar.ImmersionBar
import com.hjq.toast.ToastUtils
import com.socks.library.KLog
import com.tamsiree.rxkit.RxActivityTool
import com.tamsiree.rxkit.RxActivityTool.AppExit
import com.tamsiree.rxkit.RxActivityTool.addActivity
import com.tamsiree.rxkit.RxActivityTool.getLauncherActivity
import com.tamsiree.rxkit.RxActivityTool.isExistActivity
import com.tamsiree.rxkit.RxActivityTool.launchActivity
import com.tamsiree.rxkit.RxActivityTool.skipActivityForResult
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity
import com.xiaolu.mylibrarykotlin.R
import com.xiaolu.mylibrarykotlin.eventbean.MessageEvent
import com.xiaolu.mylibrarykotlin.utils.EventBusUtil.register
import com.xiaolu.mylibrarykotlin.utils.EventBusUtil.unregister
import com.xiaolu.mylibrarykotlin.utils.ToolbarHelper
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

/**
 * ================================================
 *
 * @ProjectName:
 * @Package: com.xiaolu.mylibrary.base
 * @ClassName: BaseRxActivity
 * @Description: Activity父类
 * @Author: 赵璐
 * @CreateDate: 2020/7/13 13:52
 * @UpdateUser: 更新者: 赵璐
 * @UpdateDate: 2020/7/13 13:52
 * @UpdateRemark: 更新内容:
 * @Version: 1.0
 * ================================================
 */
abstract class RxBaseActivity : RxAppCompatActivity(), View.OnClickListener {
    /**
     * 是否禁止旋转屏幕
     */
    private var isAllowScreenRoate = false

    /**
     * 当前Activity渲染的视图View
     */
    private var mContextView: View? = null

    /**
     * 是否强制横屏
     */
    private var isPortarait = false

    /**
     * 日志输出标志
     */
    protected val TAG = this.javaClass.simpleName
    var mContext: Context? = null

    @SuppressLint("ResourceType")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        KLog.d("onCreate", "$TAG-->onCreate()")
        mContext = this
        window = window
        addActivity(this)
        val bundle = intent.extras
        val mView = bindView()
        initParms(bundle)
        mContextView = mView ?: LayoutInflater.from(this).inflate(bindLayout(), null)
        val toolbar: Toolbar = mContextView!!.findViewById(if (setToolbarLayout() == 0) R.id.toolbar else setToolbarLayout())
        if (toolbar != null) {
            setSupportActionBar(toolbar)
            // 默认不显示原生标题
            supportActionBar!!.setDisplayShowTitleEnabled(false)
            val immersionBar = ImmersionBar.with(this)
            immersionBar.statusBarDarkFont(true)
                    .titleBar(toolbar)
                    .init()
            initToolbar(ToolbarHelper(toolbar), immersionBar)
        }
        setContentView(mContextView)
        ButterKnife.bind(this)
        if (!isAllowScreenRoate) {
            requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        }
        if (isPortarait) {
            isPortarait()
        }
        initView(mContextView)
        doBusiness(this)
        setListener()
        if (isRegisterEventBus) {
            KLog.d(TAG, "EventBusUtil --> register")
            register(this)
        }
    }

    /**
     * 设置Activity的状态
     *
     * @param window
     */
    protected fun setWindow(window: Window?) {}

    /**
     * 设置toolbar
     *
     * @param toolbarHelper
     * @return void
     * @method initToolbar
     * @description: 设置toolbar
     * @date: 2020/7/13 13:54
     * @author: xiaol
     */
    protected abstract fun initToolbar(toolbarHelper: ToolbarHelper?, immersionBar: ImmersionBar?)

    /**
     * 是否注册事件分发
     *
     * @return true绑定EventBus事件分发，默认不绑定，子类需要绑定的话复写此方法返回true.
     */
    protected val isRegisterEventBus: Boolean
        protected get() = false

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onEventBusCome(event: MessageEvent<*>?) {
        event?.let { receiveEvent(it) }
    }

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    fun onStickyEventBusCome(event: MessageEvent<*>?) {
        event?.let { receiveStickyEvent(it) }
    }

    /**
     * 接受分发事件
     *
     * @param event 分发事件
     * @return void
     * @method receiveEvent
     * @description: 描述一下方法的作用
     * @date: 2020/7/13 13:55
     * @author: 赵璐
     */
    protected fun receiveEvent(event: MessageEvent<*>?) {}

    /**
     * 接受到分发的粘性事件
     *
     * @param event 粘性事件
     * @return void
     * @method receiveEvent
     * @description: 描述一下方法的作用
     * @date: 2020/7/13 13:55
     * @author: 赵璐
     */
    protected fun receiveStickyEvent(event: MessageEvent<*>?) {}

    /**
     * 重写 getResource 方法，防止系统字体影响
     */
    override fun getResources(): Resources { //禁止app字体大小跟随系统字体大小调节
        val resources = super.getResources()
        if (resources != null && resources.configuration.fontScale != 1.0f) {
            val configuration = resources.configuration
            configuration.fontScale = 1.0f
            resources.updateConfiguration(configuration, resources.displayMetrics)
        }
        return resources
    }

    /**
     * [初始化参数]
     *
     * @param parms
     */
    abstract fun initParms(parms: Bundle?)

    /**
     * [绑定视图]
     *
     * @return
     */
    abstract fun bindView(): View?

    /**
     * [绑定布局]
     *
     * @return
     */
    abstract fun bindLayout(): Int

    /**
     * [绑定布局]
     *
     * @return
     */
    abstract fun setToolbarLayout(): Int

    /**
     * [初始化控件]
     *
     * @param view
     */
    abstract fun initView(view: View?)

    /**
     * [绑定控件]
     *
     * @param resId
     * @return
     */
    protected fun <T : View> FVBI(resId: Int): T {
        return super.findViewById(resId)
    }

    /**
     * [设置监听]
     */
    abstract fun setListener()

    /**
     * View点击
     */
    abstract fun widgetClick(v: View?)

    /**
     * 加载网络数据
     * 子类需要可以重写
     */
    fun initDate() {}
    fun initDate(url: String?) {}
    override fun onClick(v: View) {
        widgetClick(v)
    }

    /**
     * [业务操作]
     *
     * @param mContext
     */
    abstract fun doBusiness(mContext: Context?)
    override fun onRestart() {
        super.onRestart()
        KLog.d("onRestart", "$TAG-->onRestart()")
    }

    override fun onStart() {
        super.onStart()
        KLog.d("onStart", "$TAG-->onStart()")
    }

    override fun onResume() {
        super.onResume()
        //  doBusiness(this);
        KLog.d("onResume", "$TAG-->onResume()")
    }

    override fun onPause() {
        super.onPause()
        KLog.d("onPause", "$TAG-->onPause()")
    }

    override fun onStop() {
        super.onStop()
        KLog.d("onStop", "$TAG-->onStop()")
    }

    override fun onDestroy() {
        super.onDestroy()
        KLog.d("onDestroy", "$TAG-->onDestroy()")
        if (isRegisterEventBus) {
            KLog.d(TAG, "EventBusUtil-->unregister")
            unregister(this)
        }
    }

    /**
     * 显示底部Toast
     *
     * @param msg
     */
    protected fun showBottomToast(msg: String?) {
        ToastUtils.show(msg)
        ToastUtils.setGravity(Gravity.BOTTOM, 0, 50)
    }

    /**
     * 显示顶部Toast
     *
     * @param msg
     */
    protected fun showTopToast(msg: String?) {
        ToastUtils.show(msg)
        ToastUtils.setGravity(Gravity.TOP, 0, -50)
    }

    /**
     * 显示居中Toast
     *
     * @param msg
     */
    protected fun showCenterToast(msg: String?) {
        ToastUtils.show(msg)
        ToastUtils.setGravity(Gravity.CENTER, 0, 0)
    }

    /**
     * 显示自定义位置Toast
     *
     * @param gravity – 重心
     * @param xOffset – x轴偏移
     * @param yOffset – y轴偏移
     * @param msg
     */
    protected fun showToast(msg: String?, gravity: Int, xOffset: Int, yOffset: Int) {
        ToastUtils.show(msg)
        ToastUtils.setGravity(gravity, xOffset, yOffset)
    }

    /**
     * 判断是否存在指定Activity
     *
     * @param packageName 包名
     * @param className   类名
     */
    protected fun isExistActivity(packageName: String?, className: String?) {
        isExistActivity(mContext!!, packageName, className)
    }

    /**
     * 打开指定的Activity
     *
     * @param packageName 包名
     * @param className   类名
     * @param bundle
     */
    protected fun launchActivity(packageName: String?, className: String?, bundle: Bundle?) {
        launchActivity(mContext!!, packageName, className, bundle)
    }

    /**
     * 跳转到指定Activity并关闭当前Activity
     */
    protected fun skipActivityAndFinish(clazz: Class<out Activity>?) {
        RxActivityTool.skipActivityAndFinish(mContext!!, clazz)
    }

    /**
     * 跳转到指定Activity并关闭当前Activity
     */
    protected fun skipActivityAndFinish(clazz: Class<out Activity>?, bundle: Bundle?) {
        RxActivityTool.skipActivityAndFinish(mContext!!, clazz, bundle)
    }

    /**
     * 跳转到指定Activity
     */
    protected fun skipActivity(clazz: Class<out Activity>?) {
        RxActivityTool.skipActivity(mContext!!, clazz)
    }

    /**
     * 跳转到指定Activity
     */
    protected fun skipActivity(clazz: Class<out Activity>?, bundle: Bundle?) {
        RxActivityTool.skipActivity(mContext!!, clazz, bundle)
    }

    /**
     * 跳转后Finish之前所有的Activity
     */
    protected fun skipActivityAndFinishAll(clazz: Class<out Activity>?) {
        RxActivityTool.skipActivityAndFinishAll(mContext!!, clazz)
    }

    /**
     * 跳转后Finish之前所有的Activity
     */
    protected fun skipActivityAndFinishAll(clazz: Class<out Activity>?, bundle: Bundle?) {
        RxActivityTool.skipActivityAndFinishAll(mContext!!, clazz, bundle)
    }

    /**
     * activityForResult封装
     */
    protected fun skipActivityForResult(clazz: Class<out Activity>?, bundle: Bundle?, requestCode: Int) {
        skipActivityForResult(this, clazz, bundle, requestCode)
    }

    /**
     * 获取launcher activity
     *
     * @param packageName
     */
    protected fun getLauncherActivity(packageName: String?) {
        getLauncherActivity(mContext!!, packageName!!)
    }

    /**
     * 关闭当前Activity
     */
    protected fun finishActivity() {
        RxActivityTool.finishActivity()
    }

    /**
     * 关闭所有Activity
     */
    protected fun finishAllActivity() {
        finishAllActivity()
    }

    /**
     * 关闭指定Activity
     */
    protected fun finishActivity(activity: Activity?) {
        RxActivityTool.finishActivity(activity!!)
    }

    /**
     * 关闭指定类名的所有Activity
     */
    protected fun finishActivity(clz: Class<*>?) {
        finishActivity(clz)
    }

    /**
     * 退出App
     */
    protected fun exitApp() {
        AppExit(mContext!!)
    }

    /**
     * [是否允许屏幕旋转]
     * 默认为否
     *
     * @param isAllowScreenRoate
     */
    fun setScreenRoate(isAllowScreenRoate: Boolean) {
        this.isAllowScreenRoate = isAllowScreenRoate
    }

    /**
     * [是否强制横屏]
     * 默认为否
     *
     * @param portarait
     */
    fun setPortarait(portarait: Boolean) {
        isPortarait = portarait
    }

    /**
     * 判断是否是为横屏
     * ORIENTATION_LANDSCAPE 横屏
     * ORIENTATION_PORTRAIT 竖屏
     */
    private fun isPortarait() {
        val mConfiguration = this.resources.configuration //获取设置的配置信息
        val ori = mConfiguration.orientation //获取屏幕方向
        if (ori == Configuration.ORIENTATION_LANDSCAPE) {
            hideBottomUIMenu()
        } else if (ori == Configuration.ORIENTATION_PORTRAIT) {
            requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE //强制为横屏
        }
    }

    /**
     * 隐藏菜单虚拟按键
     */
    protected fun hideBottomUIMenu() {
        //隐藏虚拟按键，并且全屏
        if (Build.VERSION.SDK_INT > 11 && Build.VERSION.SDK_INT < 19) { // lower api
            val v = this.window.decorView
            v.systemUiVisibility = View.GONE
        } else if (Build.VERSION.SDK_INT >= 19) {
            //for new api versions.
            val decorView = window.decorView
            val uiOptions = (View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                    or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY or View.SYSTEM_UI_FLAG_FULLSCREEN)
            decorView.systemUiVisibility = uiOptions
        }
    }
}