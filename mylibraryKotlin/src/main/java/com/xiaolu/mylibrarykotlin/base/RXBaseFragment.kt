package com.xiaolu.mylibrarykotlin.base

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import butterknife.ButterKnife
import butterknife.Unbinder
import com.gyf.immersionbar.ImmersionBar
import com.gyf.immersionbar.components.SimpleImmersionOwner
import com.gyf.immersionbar.components.SimpleImmersionProxy
import com.hjq.toast.ToastUtils
import com.socks.library.KLog
import com.tamsiree.rxkit.RxActivityTool
import com.tamsiree.rxkit.RxActivityTool.getLauncherActivity
import com.tamsiree.rxkit.RxActivityTool.launchActivity
import com.tamsiree.rxkit.RxActivityTool.skipActivityForResult
import com.trello.rxlifecycle2.components.support.RxFragment
import com.xiaolu.mylibrarykotlin.R
import com.xiaolu.mylibrarykotlin.eventbean.MessageEvent
import com.xiaolu.mylibrarykotlin.utils.EventBusUtil.register
import com.xiaolu.mylibrarykotlin.utils.EventBusUtil.unregister
import com.xiaolu.mylibrarykotlin.utils.ToolbarHelper
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

abstract class RXBaseFragment : RxFragment(), SimpleImmersionOwner {
    /**
     * 根View
     */
    var myRootView: View? = null

    /**
     * 当做Context去使用, MainActivity
     */
    var mActivity: Activity? = null

    /**
     * 是否对用户可见
     */
    protected var mIsVisible = false

    /**
     * 是否加载完成
     * 当执行完onCreateView,View的初始化方法后方法后即为true
     */
    protected var mIsPrepare = false

    /**
     * 日志输出标志
     */
    protected val TAG = this.javaClass.simpleName
    private var bind: Unbinder? = null

    /**
     * ImmersionBar代理类
     */
    private val mSimpleImmersionProxy = SimpleImmersionProxy(this)
    private var toolbar: Toolbar? = null
    private var statusBarView: View? = null

    //fragment创建
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        KLog.d("onCreate", "$TAG-->onCreate()")
        mActivity = activity //获取fragment所依赖的activity的对象
        val bundle = arguments
        initParams(bundle)
        if (isRegisterEventBus) {
            KLog.d("EventBusRegister", "$TAG-->register()")
            register(this)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        myRootView = inflater.inflate(setLayoutResourceId(), container, false)
        KLog.d("onCreateView", "$TAG-->onCreateView()")
        return myRootView
    }

    @SuppressLint("ResourceType")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        KLog.d("onViewCreated", "$TAG-->onViewCreated()")
        toolbar = view.findViewById(if (setToolbarLayout() == 0) R.id.toolbar else setToolbarLayout())
        statusBarView = view.findViewById(R.id.status_bar_view)
        if (toolbar != null) {
            (activity as AppCompatActivity?)!!.setSupportActionBar(toolbar)
            // 默认不显示原生标题
            (activity as AppCompatActivity?)!!.supportActionBar!!.setDisplayShowTitleEnabled(false)
            initToolbar(ToolbarHelper(toolbar!!))
        }
        bind = ButterKnife.bind(this, view)
        fitsLayoutOverlap()
        initView()
        mIsPrepare = true
        setListener()
    }

    protected abstract fun setToolbarLayout(): Int
    protected fun initToolbar(toolbarHelper: ToolbarHelper?) {}

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
     * 接收到分发到事件
     *
     * @param event 事件
     */
    protected fun receiveEvent(event: MessageEvent<*>?) {}

    /**
     * 接受到分发的粘性事件
     *
     * @param event 粘性事件
     */
    protected fun receiveStickyEvent(event: MessageEvent<*>?) {}

    //fragment所在的activity创建完成
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        mSimpleImmersionProxy.onActivityCreated(savedInstanceState)
        KLog.d("onActivityCreated", "$TAG-->onActivityCreated()")
        //        initDate();
        doBusiness(mActivity)
    }

    protected abstract fun setLayoutResourceId(): Int

    /**
     * [初始化参数]
     *
     * @param bundle
     */
    abstract fun initParams(bundle: Bundle?)

    /**
     * 初始化界面
     */
    abstract fun initView()

    /**
     * [业务操作]
     *
     * @param mActivity
     */
    protected abstract fun doBusiness(mActivity: Activity?)

    /**
     * 点击事件
     */
    abstract fun setListener()

    /**
     * 初始化数据
     */
    fun initDate() {}
    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        mSimpleImmersionProxy.isUserVisibleHint = isVisibleToUser
        mIsVisible = isVisibleToUser
        if (isVisibleToUser) {
            onVisibleToUser()
        }
    }

    override fun onHiddenChanged(hidden: Boolean) {
        super.onHiddenChanged(hidden)
        mSimpleImmersionProxy.onHiddenChanged(hidden)
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        mSimpleImmersionProxy.onConfigurationChanged(newConfig)
        fitsLayoutOverlap()
    }

    override fun immersionBarEnabled(): Boolean {
        return true
    }

    private fun fitsLayoutOverlap() {
        if (statusBarView != null) {
            ImmersionBar.setStatusBarView(this, statusBarView)
        } else {
            ImmersionBar.setTitleBar(this, toolbar)
        }
    }

    /**
     * 用户可见时执行的操作
     */
    protected fun onVisibleToUser() {
        if (mIsPrepare && mIsVisible) {
            onLazyLoad()
            //数据加载完毕,恢复标记,防止重复加载
            mIsVisible = false
            mIsPrepare = false
        }
    }

    /**
     * 检查activity连接情况
     */
    fun checkActivityAttached() {
        if (activity == null) {
            throw ActivityNotAttachedException()
        }
    }

    class ActivityNotAttachedException : RuntimeException("Fragment has disconnected from Activity !")

    /**
     * 懒加载，仅当用户可见切view初始化结束后才会执行
     */
    protected fun onLazyLoad() {}

    /**
     * findViewById
     *
     * @param id
     * @param <T>
     * @return
    </T> */
    protected fun <T : View?> `$`(id: Int): T? {
        return if (myRootView == null) {
            null
        } else myRootView!!.findViewById<View>(id) as T
    }

    /**
     * [页面跳转]
     *
     * @param clz
     */
    fun startActivity(clz: Class<*>?) {
        startActivity(Intent(mActivity, clz))
    }

    /**
     * 打开指定的Activity
     *
     * @param packageName 包名
     * @param className   类名
     * @param bundle
     */
    protected fun launchActivity(packageName: String?, className: String?, bundle: Bundle?) {
        launchActivity(mActivity!!, packageName, className, bundle)
    }

    /**
     * 跳转到指定Activity并关闭当前Activity
     */
    protected fun skipActivityAndFinish(clazz: Class<out Activity>?) {
        RxActivityTool.skipActivityAndFinish(mActivity!!, clazz)
    }

    /**
     * 跳转到指定Activity并关闭当前Activity
     */
    protected fun skipActivityAndFinish(clazz: Class<out Activity>?, bundle: Bundle?) {
        RxActivityTool.skipActivityAndFinish(mActivity!!, clazz, bundle)
    }

    /**
     * 跳转到指定Activity
     */
    protected fun skipActivity(clazz: Class<out Activity>?) {
        RxActivityTool.skipActivity(mActivity!!, clazz)
    }

    /**
     * 跳转到指定Activity
     */
    protected fun skipActivity(clazz: Class<out Activity>?, bundle: Bundle?) {
        RxActivityTool.skipActivity(mActivity!!, clazz, bundle)
    }

    /**
     * 跳转后Finish之前所有的Activity
     */
    protected fun skipActivityAndFinishAll(clazz: Class<out Activity>?) {
        RxActivityTool.skipActivityAndFinishAll(mActivity!!, clazz)
    }

    /**
     * 跳转后Finish之前所有的Activity
     */
    protected fun skipActivityAndFinishAll(clazz: Class<out Activity>?, bundle: Bundle?) {
        RxActivityTool.skipActivityAndFinishAll(mActivity!!, clazz, bundle)
    }

    /**
     * activityForResult封装
     */
    protected fun skipActivityForResult(clazz: Class<out Activity>?, bundle: Bundle?, requestCode: Int) {
        skipActivityForResult(mActivity!!, clazz, bundle, requestCode)
    }

    /**
     * 获取launcher activity
     *
     * @param packageName
     */
    protected fun getLauncherActivity(packageName: String?) {
        getLauncherActivity(mActivity!!, packageName!!)
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

    override fun onResume() {
        super.onResume()
        KLog.d("onResume", "$TAG-->onResume()")
    }

    override fun onStop() {
        super.onStop()
        KLog.d("onStop", "$TAG-->onStop()")
    }

    override fun onStart() {
        super.onStart()
        KLog.d("onStart", "$TAG-->onStart()")
    }

    override fun onPause() {
        super.onPause()
        KLog.d("onPause", "$TAG-->onPause()")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        //页面销毁,恢复标记
        KLog.d("onDestroyView", "$TAG-->onDestroyView()")
        bind!!.unbind()
        mIsPrepare = false
        mIsVisible = false
    }

    override fun onDestroy() {
        super.onDestroy()
        mSimpleImmersionProxy.onDestroy()
        KLog.d("onDestroy", "$TAG-->onDestroy()")
        if (isRegisterEventBus) {
            KLog.d("EventBusUnRegister", "$TAG-->unregister()")
            unregister(this)
        }
    }
}