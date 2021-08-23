package com.xiaolu.mylibrarytool.presenter

import com.xiaolu.mylibrary.log.LogUtil
import com.xiaolu.mylibrary.mvpbase.IBasePresenter
import com.xiaolu.mylibrarytool.contract.ITextContract
import com.xiaolu.mylibrarytool.model.TextModel
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

/**
 * @describe
 * @author  admin
 * @date 2021/8/23  14:13
 * 								 - generate by MvpAutoCodePlus plugin.
 */

class TextPresenter : IBasePresenter<ITextContract.View, ITextContract.Model>(),
    ITextContract.Presenter {
    override fun createModel(): ITextContract.Model {
        return TextModel(MainScope())
    }

    override fun getBanner() {
        val launch = model.mainScope.launch {

           var r =  model.getBanner()
            LogUtil.d(r.toString())
        }

    }

}

