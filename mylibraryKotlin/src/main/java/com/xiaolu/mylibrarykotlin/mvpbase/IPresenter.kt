package com.xiaolu.mylibrarykotlin.mvpbase

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.OnLifecycleEvent

/**
 * ================================================
 *
 * @ProjectName: MyLibraryTool
 * @Package: com.xiaolu.mylibrary.mvpbase
 * @ClassName: IPresenter
 * @Description: java类作用描述
 * @Author: xiaol
 * @CreateDate: 2020/10/28 13:41
 * @UpdateUser: xiaol
 * @UpdateDate: 2020/10/28 13:41
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 * ================================================
 */
interface IPresenter<V> : LifecycleObserver where V : IView?, V : LifecycleOwner? {
    /**
     * 创建view时调用  调用在initView 之后
     *
     * @param view
     */
    fun attachView(view: V)

    /**
     * view销毁时调用释放资源
     */
    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun detachView()
}