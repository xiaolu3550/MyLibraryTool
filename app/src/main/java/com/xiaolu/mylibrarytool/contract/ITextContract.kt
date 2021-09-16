package com.xiaolu.mylibrarytool.contract

import com.xiaolu.mylibrary.mvpbase.IModel
import com.xiaolu.mylibrary.mvpbase.IPresenter
import com.xiaolu.mylibrary.mvpbase.IView
import com.xiaolu.mylibrarytool.bean.*

/**
 * @describe
 * @author  admin
 * @date 2021/8/23  14:13
 * 								 - generate by MvpAutoCodePlus plugin.
 */

interface ITextContract {
    interface View : IView {
        fun onSuccess(any: MutableList<BannerBean>?)
        fun onError(string: String)

        fun loginSuccess(any: LoginBean)
        fun loginError(string: String)

    }

    interface Presenter : IPresenter<View> {
        fun getBanner()
        fun login(loginRequestBean: LoginRequestBean)
    }

    interface Model : IModel {
        suspend fun getBanner(): BaseListBean<BannerBean>
        suspend fun login(loginRequestBean: LoginRequestBean): BaseObjectBean<LoginBean>
    }
}
