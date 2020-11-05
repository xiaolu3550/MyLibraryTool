package com.xiaolu.mylibrarykotlin.load.core

import com.xiaolu.mylibrarykotlin.load.callback.BaseCallBack

/**
 * ================================================
 *
 * @ProjectName: MyLibraryTool
 * @Package: com.xiaolu.mylibrary.load.core
 * @ClassName: Convertor
 * @Description: java类作用描述
 * @Author: xiaol
 * @CreateDate: 2020/8/18 11:16
 * @UpdateUser: xiaol
 * @UpdateDate: 2020/8/18 11:16
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 * ================================================
 */
interface Convertor<T> {
    fun map(t: T): Class<out BaseCallBack?>?
}