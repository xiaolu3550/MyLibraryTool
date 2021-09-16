package com.xiaolu.mylibrary.mvpbase

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.cancel


public interface IBaseModel {
    var mainScope: CoroutineScope
    fun onDestroy() {
        mainScope.cancel()
    }

}