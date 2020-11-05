package com.xiaolu.mylibrarykotlin.load.callback

import android.content.Context
import android.view.View
import java.io.*

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
abstract class BaseCallBack : Serializable {
    private var rootView: View? = null
    private var context: Context? = null
    private var onReloadListener: OnReloadListener? = null

    /**
     * 如果返回true，则在附加回调视图时将显示successView。
     */
    var successVisible = false

    constructor() {}
    internal constructor(view: View?, context: Context?, onReloadListener: OnReloadListener?) {
        rootView = view
        this.context = context
        this.onReloadListener = onReloadListener
    }

    fun setCallBack(context: Context?, onReloadListener: OnReloadListener?): BaseCallBack {
        this.context = context
        this.onReloadListener = onReloadListener
        return this
    }

    fun getRootView(): View? {
        val resId = onCreateView()
        if (resId == 0 && rootView != null) {
            return rootView
        }
        if (onBuildView(context) != null) {
            rootView = onBuildView(context)
        }
        if (rootView == null) {
            rootView = View.inflate(context, onCreateView(), null)
        }
        rootView!!.setOnClickListener(View.OnClickListener { v ->
            if (onReloadEvent(context, rootView)) {
                return@OnClickListener
            }
            if (onReloadListener != null) {
                onReloadListener!!.onReload(v)
            }
        })
        onViewCreate(context, rootView)
        return rootView
    }

    protected open fun onBuildView(context: Context?): View? {
        return null
    }

    @Deprecated("Use {@link #onReloadEvent(Context context, View view)} instead.")
    protected fun onRetry(context: Context?, view: View?): Boolean {
        return false
    }

    protected fun onReloadEvent(context: Context?, view: View?): Boolean {
        return false
    }

    fun copy(): BaseCallBack? {
        val bao = ByteArrayOutputStream()
        val oos: ObjectOutputStream
        var obj: Any? = null
        try {
            oos = ObjectOutputStream(bao)
            oos.writeObject(this)
            oos.close()
            val bis = ByteArrayInputStream(bao.toByteArray())
            val ois = ObjectInputStream(bis)
            obj = ois.readObject()
            ois.close()
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return obj as BaseCallBack?
    }

    fun obtainRootView(): View? {
        if (rootView == null) {
            rootView = View.inflate(context, onCreateView(), null)
        }
        return rootView
    }

    interface OnReloadListener : Serializable {
        fun onReload(v: View?)
    }

    protected abstract fun onCreateView(): Int

    /**
     * 之后立即致电 [.onCreateView]
     *
     */
    protected open fun onViewCreate(context: Context?, view: View?) {}

    /**
     * 当BaseCallBack的rootView附加到其LoadLayout时调用。
     *
     */
    fun onAttach(context: Context?, view: View?) {}

    /**
     * 从其LoadLayout中删除BaseCallBack的rootView时调用。
     *
     */
    fun onDetach() {}
}