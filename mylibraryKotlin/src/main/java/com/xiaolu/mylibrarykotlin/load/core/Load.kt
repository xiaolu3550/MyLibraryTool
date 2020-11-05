package com.xiaolu.mylibrarykotlin.load.core

import com.xiaolu.mylibrarykotlin.load.LoadUtil.getTargetContext
import com.xiaolu.mylibrarykotlin.load.callback.BaseCallBack
import com.xiaolu.mylibrarykotlin.load.target.ActivityTarget
import com.xiaolu.mylibrarykotlin.load.target.ITarget
import com.xiaolu.mylibrarykotlin.load.target.ViewTarget
import java.util.*

/**
 * ================================================
 *
 * @ProjectName: MyLibraryTool
 * @Package: com.xiaolu.mylibrary.load.core
 * @ClassName: Load
 * @Description: java类作用描述
 * @Author: xiaol
 * @CreateDate: 2020/8/18 11:20
 * @UpdateUser: xiaol
 * @UpdateDate: 2020/8/18 11:20
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 * ================================================
 */
class Load {
    private var builder: Builder

    private constructor() {
        builder = Builder()
    }

    private fun setBuilder(builder: Builder) {
        this.builder = builder
    }

    private constructor(builder: Builder) {
        this.builder = builder
    }

    fun <T> register(target: Any): LoadService<T> {
        return register<T>(target, null, null)
    }

    @JvmOverloads
    fun <T> register(target: Any?, onReloadListener: BaseCallBack.OnReloadListener?, convertor: Convertor<T>? = null): LoadService<T> {
        val targetContext = getTargetContext(target!!, builder.getTargetContextList())
        val loadLayout = targetContext.replaceView(target, onReloadListener)
        return LoadService(convertor, loadLayout!!, builder)
    }

    class Builder {
        private val callbacks: MutableList<BaseCallBack> = ArrayList()
        private val targetContextList: MutableList<ITarget> = ArrayList()
        var defaultCallback: Class<out BaseCallBack>? = null
            private set

        fun addCallback(callback: BaseCallBack): Builder {
            callbacks.add(callback)
            return this
        }

        /**
         * @param targetContext
         * @return Builder
         * @since 1.3.8
         */
        fun addTargetContext(targetContext: ITarget): Builder {
            targetContextList.add(targetContext)
            return this
        }

        fun getTargetContextList(): List<ITarget> {
            return targetContextList
        }

        fun setDefaultCallback(defaultCallback: Class<out BaseCallBack>): Builder {
            this.defaultCallback = defaultCallback
            return this
        }

        fun getCallbacks(): List<BaseCallBack> {
            return callbacks
        }

        fun commit() {
            default!!.setBuilder(this)
        }

        fun build(): Load {
            return Load(this)
        }

        init {
            targetContextList.add(ActivityTarget())
            targetContextList.add(ViewTarget())
        }
    }

    companion object {
        @Volatile
        private var loadSir: Load? = null
        val default: Load?
            get() {
                if (loadSir == null) {
                    synchronized(Load::class.java) {
                        if (loadSir == null) {
                            loadSir = Load()
                        }
                    }
                }
                return loadSir
            }

        fun beginBuilder(): Builder {
            return Builder()
        }
    }
}