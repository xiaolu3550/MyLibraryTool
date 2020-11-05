package com.xiaolu.mylibrarykotlin.mvpbase

import androidx.lifecycle.LifecycleOwner
import com.trello.rxlifecycle2.LifecycleTransformer

/**
 * ================================================
 *
 * @ProjectName: MyLibraryTool
 * @Package: com.xiaolu.mylibrary.mvpbase
 * @ClassName: IView
 * @Description: java类作用描述
 * @Author: xiaol
 * @CreateDate: 2020/10/28 13:35
 * @UpdateUser: xiaol
 * @UpdateDate: 2020/10/28 13:35
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 * ================================================
 */
interface IView : LifecycleOwner {
    /**
     * rxJavaLife
     *
     * @param <T>
     * @return
    </T> */
    fun <T> bindToLifecycleS(): LifecycleTransformer<T>?
}