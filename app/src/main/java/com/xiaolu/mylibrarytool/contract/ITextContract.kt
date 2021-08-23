package com.xiaolu.mylibrarytool.contract

import com.xiaolu.mylibrary.mvpbase.IModel
import com.xiaolu.mylibrary.mvpbase.IPresenter
import com.xiaolu.mylibrary.mvpbase.IView

/**
 * @describe
 * @author  admin
 * @date 2021/8/23  14:13
 * 								 - generate by MvpAutoCodePlus plugin.
 */

interface ITextContract {
    interface View : IView {}
    interface Presenter : IPresenter<View> {
        fun getBanner()
    }
    interface Model : IModel {
        suspend fun getBanner() :Any
    }
}
