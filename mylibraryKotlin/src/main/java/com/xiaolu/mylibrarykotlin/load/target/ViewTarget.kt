package com.xiaolu.mylibrarykotlin.load.target

import android.view.View
import android.view.ViewGroup
import com.xiaolu.mylibrarykotlin.load.callback.BaseCallBack
import com.xiaolu.mylibrarykotlin.load.callback.SuccessCallBack
import com.xiaolu.mylibrarykotlin.load.core.LoadLayout

/**
 * ================================================
 *
 * @ProjectName: MyLibraryTool
 * @Package: com.xiaolu.mylibrary.load.target
 * @ClassName: ViewTarget
 * @Description: java类作用描述
 * @Author: xiaol
 * @CreateDate: 2020/8/18 11:25
 * @UpdateUser: xiaol
 * @UpdateDate: 2020/8/18 11:25
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 * ================================================
 */
class ViewTarget : ITarget {
    override fun equals(target: Any?): Boolean {
        return target is View
    }

    override fun replaceView(target: Any?, onReloadListener: BaseCallBack.OnReloadListener?): LoadLayout? {
        val oldContent = target as View?
        val contentParent = oldContent!!.parent as ViewGroup
        var childIndex = 0
        val childCount = contentParent?.childCount ?: 0
        for (i in 0 until childCount) {
            if (contentParent.getChildAt(i) === oldContent) {
                childIndex = i
                break
            }
        }
        contentParent?.removeView(oldContent)
        val oldLayoutParams = oldContent.layoutParams
        val loadLayout = LoadLayout(oldContent.context, onReloadListener)
        loadLayout.setupSuccessLayout(SuccessCallBack(oldContent, oldContent.context, onReloadListener))
        contentParent?.addView(loadLayout, childIndex, oldLayoutParams)
        return loadLayout
    }
}