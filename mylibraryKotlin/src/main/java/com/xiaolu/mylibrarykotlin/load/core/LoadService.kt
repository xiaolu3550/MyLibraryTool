package com.xiaolu.mylibrarykotlin.load.core

import android.content.Context
import android.os.Handler
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import com.xiaolu.mylibrarykotlin.load.callback.BaseCallBack
import com.xiaolu.mylibrarykotlin.load.callback.SuccessCallBack

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
class LoadService<T> internal constructor(private val convertor: Convertor<T>?, val loadLayout: LoadLayout, builder: Load.Builder) {
    private val TAG = javaClass.simpleName
    private fun initCallback(builder: Load.Builder) {
        val callbacks: List<BaseCallBack>? = builder.getCallbacks()
        val defalutCallback: Class<out BaseCallBack>? = builder.defaultCallback
        if (callbacks != null && callbacks.size > 0) {
            for (callback in callbacks) {
                loadLayout.setupCallback(callback)
            }
        }
        Handler().post {
            if (defalutCallback != null) {
                loadLayout.showCallback(defalutCallback)
            }
        }
    }

    fun showSuccess() {
        loadLayout.showCallback(SuccessCallBack::class.java)
    }

    fun showCallback(callback: Class<out BaseCallBack?>?) {
        loadLayout.showCallback(callback!!)
    }

    fun showWithConvertor(t: T) {
        requireNotNull(convertor) { "You haven't set the Convertor." }
        convertor.map(t)?.let { loadLayout.showCallback(it) }
    }

    val currentCallback: Class<out BaseCallBack>?
        get() = loadLayout.currentCallback

    /**
     * 如果希望将工具栏保留在Fragment中，请获取rootView
     *
     */
    @Deprecated("")
    fun getTitleLoadLayout(context: Context?, rootView: ViewGroup, titleView: View?): LinearLayout {
        val newRootView = LinearLayout(context)
        newRootView.orientation = LinearLayout.VERTICAL
        val layoutParams = LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT)
        newRootView.layoutParams = layoutParams
        rootView.removeView(titleView)
        newRootView.addView(titleView)
        newRootView.addView(loadLayout, layoutParams)
        return newRootView
    }

    /**
     * 动态修改回调
     *
     * @param callback  您要修改哪个回调（布局，事件）
     * @param transport 接口包含修改逻辑
     */
    fun setCallBack(callback: Class<out BaseCallBack?>?, transport: Transport?): LoadService<T> {
        loadLayout.setCallBack(callback!!, transport)
        return this
    }

    init {
        initCallback(builder)
    }
}