package com.xiaolu.mylibrarytool.bean

import com.xiaolu.mylibrary.net.ApiException
import com.xiaolu.mylibrarytool.bean.BaseListBean
import com.xiaolu.mylibrarytool.bean.BaseObjectBean

val <T> BaseListBean<T>.parseListData: MutableList<T>
    get() = if(isSuccess) data else throw ApiException(
        code,
        msg
    )

val <T> BaseObjectBean<T>.parseData
    get() = if(isSuccess) data else throw ApiException(
        code,
        msg
    )