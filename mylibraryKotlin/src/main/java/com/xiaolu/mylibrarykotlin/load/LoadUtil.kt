package com.xiaolu.mylibrarykotlin.load

import android.os.Looper
import com.xiaolu.mylibrarykotlin.load.target.ITarget

/**
 * ================================================
 *
 * @ProjectName: MyLibraryTool
 * @Package: com.xiaolu.mylibrary.load
 * @ClassName: LoadUtil
 * @Description: java类作用描述
 * @Author: xiaol
 * @CreateDate: 2020/8/18 11:15
 * @UpdateUser: xiaol
 * @UpdateDate: 2020/8/18 11:15
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 * ================================================
 */
object LoadUtil {
    val isMainThread: Boolean
        get() = Looper.myLooper() == Looper.getMainLooper()

    fun getTargetContext(target: Any, targetContextList: List<ITarget>): ITarget {
        for (targetContext in targetContextList) {
            if (targetContext == target) {
                return targetContext
            }
        }
        throw IllegalArgumentException("No TargetContext fit it")
    }
}