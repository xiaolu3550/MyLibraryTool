package com.xiaolu.mylibrarykotlin.mvpbase

import android.content.Context
import com.trello.rxlifecycle2.LifecycleTransformer

/**
 * ================================================
 *
 * @ProjectName: MyLibraryTool
 * @Package: com.xiaolu.mylibrary.mvpbase
 * @ClassName: BaseContract
 * @Description: java类作用描述
 * @Author: xiaol
 * @CreateDate: 2020/8/11 10:17
 * @UpdateUser: xiaol
 * @UpdateDate: 2020/8/11 10:17
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 * ================================================
 */
interface BaseContract {
    interface BaseModel
    interface BasePresenter
    interface BaseView {
        /**
         * 显示请求错误提示
         *
         * @param message 错误信息
         */
        fun onError(message: String?)

        /**
         * 显示请求加载提示
         */
        fun onLoad()

        /**
         * 加载成功
         */
        fun onDisMiss()

        /**
         * 加载数据为null时
         */
        fun onEmpty()

        /**
         * 获取上下文
         *
         * @return context
         */
        val context: Context?

        /**
         * 绑定RXLifecycle生命周期
         *
         * @param <T>
         * @return
        </T> */
        fun <T> bindToLifecycleS(): LifecycleTransformer<T>?
    }
}