package com.xiaolu.mylibrarykotlin.base

import android.os.Bundle
import android.util.SparseArray
import android.view.*
import androidx.annotation.IdRes
import androidx.annotation.LayoutRes
import androidx.annotation.StyleRes
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import com.socks.library.KLog

/**
 * @ClassName: BaseDialog
 * @Description: : (dialog 基类)
 */
abstract class BaseDialog : DialogFragment() {
    protected val TAG = this.javaClass.simpleName

    enum class DialogType {
        mid, bottom, top, full, right
    }

    protected var mRootView: View? = null
    private var mViews: SparseArray<View?>? = null
    var window: Window? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        KLog.d("onCreate", "$TAG-->onCreate()")
        setStyle(STYLE_NORMAL, dialogStyle)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        KLog.d("onCreateView", "$TAG-->onCreateView()")
        if (mRootView == null) {
            mRootView = inflater.inflate(dialogLayout, container)
            mViews = SparseArray()
            window = dialog!!.window
        } else {
            val parent = mRootView!!.parent as ViewGroup
            parent?.removeView(mRootView)
        }
        return mRootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        KLog.d("onViewCreated", "$TAG-->onViewCreated()")
        bindView(savedInstanceState)
    }

    /**
     * 绑定视图
     *
     * @param bundle savedInstanceState
     */
    protected fun bindView(bundle: Bundle?) {
        build(DialogType.mid)
        this.dialog!!.setCanceledOnTouchOutside(false)
    }

    /**
     * 再次根据选择的类型进行布局设置
     *
     * @param type 风格
     * @return dialog
     */
    protected fun build(type: DialogType?): BaseDialog {
        when (type) {
            DialogType.mid -> {
            }
            DialogType.bottom -> initBottomDialog()
            DialogType.top -> {
            }
            DialogType.full -> {
            }
            DialogType.right -> initRightiDialog()
            else -> {
            }
        }
        return this
    }

    protected fun initBottomDialog() {
        if (null != window) {
            window!!.decorView.setPadding(0, 0, 0, 0) //消除边距
            val lp = window!!.attributes
            lp.width = WindowManager.LayoutParams.MATCH_PARENT
            lp.height = WindowManager.LayoutParams.WRAP_CONTENT
            //设置dialog的位置在底部
            lp.gravity = Gravity.BOTTOM
            window!!.attributes = lp
            window!!.setGravity(Gravity.BOTTOM)
        }
    }

    protected fun initRightiDialog() {
        if (null != window) {
            window!!.decorView.setPadding(0, 0, 0, 0) //消除边距
            val lp = window!!.attributes
            lp.width = WindowManager.LayoutParams.WRAP_CONTENT
            lp.height = WindowManager.LayoutParams.MATCH_PARENT
            //设置dialog的位置在左侧
            lp.gravity = Gravity.RIGHT
            window!!.attributes = lp
            window!!.setGravity(Gravity.RIGHT)
        }
    }

    fun setAnimations(@StyleRes animationId: Int) {
        if (null != window) window!!.setWindowAnimations(animationId)
    }

    fun show(activity: FragmentActivity) {
        if (null != this.dialog && this.dialog!!.isShowing) {
            return
        }
        //        show(activity.getSupportFragmentManager(), getDialogTag());
        show(activity.supportFragmentManager)
    }

    fun show(fragment: Fragment) {
        if (null != this.dialog && this.dialog!!.isShowing) {
            return
        }
        //        show(fragment.getFragmentManager(), getDialogTag());
        show(fragment.fragmentManager)
    }

    fun show(fm: FragmentManager?) {
        //这里直接调用show方法会报java.lang.IllegalStateException: Can not perform this action after onSaveInstanceState
        //因为show方法中是通过commit进行的提交(通过查看源码)
        //这里为了修复这个问题，使用commitAllowingStateLoss()方法
        //注意：DialogFragment是继承自android.app.Fragment，这里要注意同v4包中的Fragment区分，别调用串了
        //DialogFragment有自己的好处，可能也会带来别的问题
        //dialog.show(getFragmentManager(), "SignDialog");
        val ft = fm!!.beginTransaction()
        ft.add(this, dialogTag)
        ft.commitAllowingStateLoss()
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        this.dialog!!.setCanceledOnTouchOutside(isCancelableOnTouchOutside)
        this.dialog!!.setCancelable(isCancelable)
    }

    protected val isCancelableOnTouchOutside: Boolean
        protected get() = true

    override fun dismiss() {
        if (null == this.dialog || !this.dialog!!.isShowing) {
            return
        }
        super.dismiss()
    }

    //获取dialog的标签
    protected abstract val dialogTag: String?

    //获取dialog的布局
    @get:LayoutRes
    protected abstract val dialogLayout: Int

    //获取dialog的风格
    @get:StyleRes
    protected abstract val dialogStyle: Int

    /**
     * fragment中可以通过这个方法直接找到需要的view，而不需要进行类型强转
     *
     * @param viewId view 资源ID
     * @param <E>    view 类型
     * @return view
    </E> */
    //@Deprecated
    protected fun <E : View?> findViewById(@IdRes viewId: Int): E? {
        if (mRootView != null) {
            var view = mViews!![viewId] as E?
            if (view == null) {
                view = mRootView!!.findViewById<View>(viewId) as E
                mViews!!.put(viewId, view)
            }
            return view
        }
        return null
    }
}