package com.xiaolu.mylibrarykotlin.base

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import butterknife.ButterKnife
import com.socks.library.KLog
import com.trello.rxlifecycle2.components.support.RxFragment

abstract class BaseLazyFragment : RxFragment() {
    val TAG = javaClass.simpleName

    /**
     * 当做Context去使用, MainActivity
     */
    var mActivity: Activity? = null
    var mHaveLoadData: Boolean = false // 表示是否已经请求过数据 = false
    var mLoadDataFinished: Boolean = false// 表示数据是否已经请求完毕 = false
    private var mRootView: View? = null

    // 表示开始加载数据, 但不表示数据加载已经完成
    abstract fun loadDataStart()

    // 表示找控件完成, 给控件们设置数据不会报空指针了
    var mViewInflateFinished = false
    override fun onAttach(activity: Activity) {
        super.onAttach(activity)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        KLog.d("onCreate", "$TAG-->onCreate()")
        mActivity = activity //获取fragment所依赖的activity的对象
        val bundle = arguments
        initParams(bundle)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        KLog.d("onActivityCreated", "$TAG-->onActivityCreated()")
        doBusiness(mActivity)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        KLog.d("onCreateView", "$TAG-->onCreateView()")
        if (mRootView != null) {
            return mRootView
        }
        mRootView = inflater.inflate(layoutId, container, false)
        ButterKnife.bind(this, mRootView!!)
        mViewInflateFinished = true
        return mRootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        KLog.d("onViewCreated", "$TAG-->onViewCreated()")
    }

    protected abstract fun initParams(bundle: Bundle?)
    protected abstract fun doBusiness(mActivity: Activity?)
    protected abstract val layoutId: Int

    /**
     * findViewById
     *
     * @param id
     * @param <T>
     * @return
    </T> */
    protected fun <T : View?> `$`(id: Int): T? {
        return if (mRootView == null) {
            null
        } else mRootView!!.findViewById<View>(id) as T
    }

    override fun onStart() {
        super.onStart()
        KLog.d("onStart", "$TAG-->onStart()")
    }

    override fun onResume() {
        super.onResume()
        KLog.d("onResume", "$TAG-->onResume()")
    }

    override fun onStop() {
        super.onStop()
        KLog.d("onStop", "$TAG-->onStop()")
    }

    override fun onPause() {
        super.onPause()
        KLog.d("onPause", "$TAG-->onPause()")
    }

    override fun onDestroy() {
        super.onDestroy()
        KLog.d("onDestroy", "$TAG-->onDestroy()")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        KLog.d("onDestroyView", "$TAG-->onDestroyView()")
        ButterKnife.bind(activity!!).unbind()
    }

    override fun onDetach() {
        super.onDetach()
        KLog.d("onDetach", "$TAG-->onDetach()")
    }

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        // 如果还没有加载过数据 && 用户切换到了这个fragment
        // 那就开始加载数据
        if (!mHaveLoadData && isVisibleToUser) {
            loadDataStart()
            mHaveLoadData = true
        }
    }
}