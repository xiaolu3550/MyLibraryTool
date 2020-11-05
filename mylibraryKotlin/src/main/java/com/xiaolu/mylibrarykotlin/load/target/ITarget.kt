package com.xiaolu.mylibrarykotlin.load.target

import com.xiaolu.mylibrarykotlin.load.callback.BaseCallBack
import com.xiaolu.mylibrarykotlin.load.core.LoadLayout

/**
 * ================================================
 *
 * @ProjectName: MyLibraryTool
 * @Package: com.xiaolu.mylibrary.load.target
 * @ClassName: ITarget
 * @Description: java类作用描述
 * @Author: xiaol
 * @CreateDate: 2020/8/18 11:23
 * @UpdateUser: xiaol
 * @UpdateDate: 2020/8/18 11:23
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 * ================================================
 */
interface ITarget {
    /**
     * @param target
     * @return
     */
    override fun equals(target: Any?): Boolean

    /**
     * 1.removeView 2.确定LP 3.addView
     *
     * @param target
     * @param onReloadListener
     * @return
     */
    fun replaceView(target: Any?, onReloadListener: BaseCallBack.OnReloadListener?): LoadLayout?
}