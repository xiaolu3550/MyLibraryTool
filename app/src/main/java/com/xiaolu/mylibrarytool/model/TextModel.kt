package com.xiaolu.mylibrarytool.model

import com.xiaolu.mylibrarytool.App
import com.xiaolu.mylibrarytool.contract.ITextContract
import kotlinx.coroutines.CoroutineScope

/**
 * @describe
 * @author  admin
 * @date 2021/8/23  14:13
 * 								 - generate by MvpAutoCodePlus plugin.
 */

class TextModel(override var mainScope: CoroutineScope) : ITextContract.Model {
    override suspend fun getBanner(): Any {
        return App.getClient().getBanner()
    }

}

