package com.xiaolu.mylibrarykotlin.load.core

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import com.xiaolu.mylibrarykotlin.load.LoadUtil
import com.xiaolu.mylibrarykotlin.load.callback.BaseCallBack
import com.xiaolu.mylibrarykotlin.load.callback.SuccessCallBack
import java.util.*

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
class LoadLayout(context: Context) : FrameLayout(context) {
    private val TAG = javaClass.simpleName
    private val callbacks: MutableMap<Class<out BaseCallBack>, BaseCallBack?> = HashMap()
    private var mContext: Context? = null
    private var onReloadListener: BaseCallBack.OnReloadListener? = null
    private var preCallback: Class<out BaseCallBack>? = null
    var currentCallback: Class<out BaseCallBack>? = null
        private set

    constructor(context: Context, onReloadListener: BaseCallBack.OnReloadListener?) : this(context) {
        this.mContext = context
        this.onReloadListener = onReloadListener
    }

    fun setupSuccessLayout(callback: BaseCallBack) {
        addCallback(callback)
        val successView = callback.getRootView()
        successView!!.visibility = View.INVISIBLE
        addView(successView, ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT))
        currentCallback = SuccessCallBack::class.java
    }

    fun setupCallback(callback: BaseCallBack) {
        val cloneCallback = callback.copy()
        cloneCallback!!.setCallBack(context, onReloadListener)
        addCallback(cloneCallback)
    }

    fun addCallback(callback: BaseCallBack?) {
        if (!callbacks.containsKey(callback!!.javaClass)) {
            callbacks[callback.javaClass] = callback
        }
    }

    fun showCallback(callback: Class<out BaseCallBack>) {
        checkCallbackExist(callback)
        if (LoadUtil.isMainThread) {
            showCallbackView(callback)
        } else {
            postToMainThread(callback)
        }
    }

    private fun postToMainThread(status: Class<out BaseCallBack>) {
        post { showCallbackView(status) }
    }

    private fun showCallbackView(status: Class<out BaseCallBack>) {
        if (preCallback != null) {
            if (preCallback == status) {
                return
            }
            callbacks[preCallback!!]!!.onDetach()
        }
        if (childCount > 1) {
            removeViewAt(CALLBACK_CUSTOM_INDEX)
        }
        for (key in callbacks.keys) {
            if (key == status) {
                val successCallback: SuccessCallBack? = callbacks[SuccessCallBack::class.java] as SuccessCallBack?
                if (key == SuccessCallBack::class.java) {
                    successCallback?.show()
                } else {
                    successCallback?.showWithCallback(callbacks[key]!!.successVisible)
                    val rootView = callbacks[key]!!.getRootView()
                    addView(rootView)
                    callbacks[key]!!.onAttach(context, rootView)
                }
                preCallback = status
            }
        }
        currentCallback = status
    }

    fun setCallBack(callback: Class<out BaseCallBack>, transport: Transport?) {
        if (transport == null) {
            return
        }
        checkCallbackExist(callback)
        transport.order(context, callbacks[callback]!!.obtainRootView())
    }

    private fun checkCallbackExist(callback: Class<out BaseCallBack>) {
        require(callbacks.containsKey(callback)) {
            String.format("The Callback (%s) is nonexistent.", callback
                    .simpleName)
        }
    }

    companion object {
        private const val CALLBACK_CUSTOM_INDEX = 1
    }
}