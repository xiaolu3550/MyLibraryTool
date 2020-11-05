package com.xiaolu.mylibrarykotlin.load.callback

import android.content.Context
import android.view.View

/**
 * ================================================
 *
 * @ProjectName: MyLibraryTool
 * @Package: com.xiaolu.mylibrary.load.callback
 * @ClassName: SuccessCallback
 * @Description: java类作用描述
 * @Author: xiaol
 * @CreateDate: 2020/8/18 11:13
 * @UpdateUser: xiaol
 * @UpdateDate: 2020/8/18 11:13
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 * ================================================
 */
class SuccessCallBack(view: View?, context: Context?, onReloadListener: OnReloadListener?) : BaseCallBack(view, context, onReloadListener) {
    override fun onCreateView(): Int {
        return 0
    }

    @Deprecated("Use {@link #showWithCallback(boolean successVisible)} instead.")
    fun hide() {
        obtainRootView()!!.visibility = View.INVISIBLE
    }

    fun show() {
        obtainRootView()!!.visibility = View.VISIBLE
    }

    fun showWithCallback(successVisible: Boolean) {
        obtainRootView()!!.visibility = if (successVisible) View.VISIBLE else View.INVISIBLE
    }
}