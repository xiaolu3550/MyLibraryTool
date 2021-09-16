package com.xiaolu.mylibrarytool.model

import com.xiaolu.mylibrary.mvpbase.BaseModel
import com.xiaolu.mylibrary.mvpbase.IBaseModel
import com.xiaolu.mylibrary.net.RequestBodyUtils
import com.xiaolu.mylibrarytool.App
import com.xiaolu.mylibrarytool.bean.*
import com.xiaolu.mylibrarytool.contract.ITextContract
import kotlinx.coroutines.CoroutineScope

/**
 * @describe
 * @author  admin
 * @date 2021/8/23  14:13
 * 								 - generate by MvpAutoCodePlus plugin.
 */

class TextModel : BaseModel(), ITextContract.Model {
    override suspend fun getBanner(): BaseListBean<BannerBean> {
        return App.getClient().getBanner()
    }

    override suspend fun login(loginRequestBean: LoginRequestBean): BaseObjectBean<LoginBean> {
        return App.getClient()
            .login(RequestBodyUtils.setRequestBody_JSON(loginRequestBean.toString()))
    }

}

