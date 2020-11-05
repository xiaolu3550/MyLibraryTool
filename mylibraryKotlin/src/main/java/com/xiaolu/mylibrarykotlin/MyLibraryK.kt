package com.xiaolu.mylibrarykotlin

import android.app.Application
import com.hjq.toast.ToastUtils
import com.socks.library.KLog
import com.tamsiree.rxkit.RxAppTool
import com.tamsiree.rxkit.RxTool

/**
 * ================================================
 * @ProjectName:    MyLibraryTool
 * @Package:        com.xiaolu.mylibrarykotlin
 * @ClassName:      MyLibraryK
 * @Description:     java类作用描述
 * @Author:          xiaol
 * @CreateDate:     2020/11/4 14:38
 * @UpdateUser:     xiaol
 * @UpdateDate:     2020/11/4 14:38
 * @UpdateRemark:   更新说明
 * @Version:        1.0
 * ================================================
 */
class MyLibraryK {
    companion object {
        private var myLibraryK: MyLibraryK? = null
        private var application: Application? = null
    }


    @get:Synchronized
    val instance: MyLibraryK?
        get() {
            if (myLibraryK == null) {
                myLibraryK = MyLibraryK()
            }
            return myLibraryK
        }

    fun init(context: Application, enabled: Boolean): MyLibraryK {
        application = context
        ToastUtils.init(context)
        RxTool.init(context)
                .crashProfile()
                .enabled(enabled)
                .apply()
        return this
    }

    fun isDebug(isDebug :Boolean) : MyLibraryK{
        KLog.init(isDebug, application?.let { RxAppTool.getAppName(it) })
        return this
    }

}