package com.xiaolu.mylibrarykotlin.net

import com.xiaolu.mylibrarykotlin.net.RxManager.Companion.instance
import io.reactivex.Observer
import io.reactivex.disposables.Disposable

/**
 * ================================================
 *
 * @ProjectName: MyLibraryTool
 * @Package: com.xiaolu.mylibrary.net
 * @ClassName: RxObserver
 * @Description: java类作用描述
 * @Author: xiaol
 * @CreateDate: 2020/11/3 16:49
 * @UpdateUser: xiaol
 * @UpdateDate: 2020/11/3 16:49
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 * ================================================
 */
abstract class RxObserver<T>(private val mKey: String) : Observer<T> {
    private val rxApiManager: RxManager? = instance
    override fun onSubscribe(d: Disposable) {
        rxApiManager!!.add(mKey, d)
    }

    override fun onNext(t: T) {
        onSuccess(t)
    }

    override fun onError(e: Throwable) {
        onErrors(e)
    }

    override fun onComplete() {}
    abstract fun onSuccess(t: T)
    abstract fun onErrors(e: Throwable)

}