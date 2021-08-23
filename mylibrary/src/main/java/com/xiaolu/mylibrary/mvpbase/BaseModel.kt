package com.xiaolu.mylibrary.mvpbase

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.cancel


public interface BaseModel {
    var mainScope: CoroutineScope
    fun onDestroy() {
        mainScope.cancel()
    }

}