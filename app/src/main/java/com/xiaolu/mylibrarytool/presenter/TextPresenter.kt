package com.xiaolu.mylibrarytool.presenter

import com.xiaolu.mylibrary.mvpbase.IBasePresenter
import com.xiaolu.mylibrary.net.launchGlobal
import com.xiaolu.mylibrary.net.onError
import com.xiaolu.mylibrarytool.bean.LoginRequestBean
import com.xiaolu.mylibrarytool.bean.parseData
import com.xiaolu.mylibrarytool.bean.parseListData
import com.xiaolu.mylibrarytool.contract.ITextContract
import com.xiaolu.mylibrarytool.model.TextModel

/**
 * @describe
 * @author  admin
 * @date 2021/8/23  14:13
 * 								 - generate by MvpAutoCodePlus plugin.
 */

class TextPresenter : IBasePresenter<ITextContract.View, ITextContract.Model>(),
    ITextContract.Presenter {
    override fun createModel(): ITextContract.Model {
        return TextModel()
    }

    override fun getBanner() {
        launchGlobal(model.mainScope, {
            view.onError(onError(it).message.toString())
        }) {
            view.onSuccess(model.getBanner().parseListData)
        }
    }

    override fun login(loginRequestBean: LoginRequestBean) {
        launchGlobal(model.mainScope, {
            view.loginError(onError(it).message.toString())
        }) {
            view.loginSuccess(model.login(loginRequestBean).parseData)
        }
    }

}

