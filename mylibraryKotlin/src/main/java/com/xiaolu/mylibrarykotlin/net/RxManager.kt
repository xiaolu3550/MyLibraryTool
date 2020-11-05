package com.xiaolu.mylibrarykotlin.net

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import java.util.*

/**
 * ================================================
 *
 * @ProjectName: MyLibraryTool
 * @Package: com.xiaolu.mylibrary.net
 * @ClassName: RxApiManager
 * @Description: java类作用描述
 * @Author: xiaol
 * @CreateDate: 2020/11/3 15:53
 * @UpdateUser: xiaol
 * @UpdateDate: 2020/11/3 15:53
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 * ================================================
 */
class RxManager private constructor() {

    private val map: MutableMap<String, CompositeDisposable>

    fun add(key: String, disposable: Disposable?) {
        val keySet: Set<String> = map.keys
        if (keySet.contains(key)) {
            val compositeDisposable = map[key]
            compositeDisposable!!.add(disposable!!)
        } else {
            val compositeDisposable = CompositeDisposable()
            compositeDisposable.add(disposable!!)
            map[key] = compositeDisposable
        }
    }

    fun clear(key: String?) {
        val keySet: Set<String> = map.keys
        if (keySet.contains(key)) {
            val compositeDisposable = map[key]
            compositeDisposable!!.clear()
            map.remove(key)
        }
    }

    companion object {
        var instance: RxManager? = null
            get() {
                if (field == null) {
                    synchronized(RxManager::class.java) {
                        if (field == null) {
                            field = RxManager()
                        }
                    }
                }
                return field
            }
    }

    init {
        map = HashMap()
    }
}