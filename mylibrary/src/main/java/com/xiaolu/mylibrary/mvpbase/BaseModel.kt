package com.xiaolu.mylibrary.mvpbase

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope

open class BaseModel : IBaseModel {
    override var mainScope: CoroutineScope = MainScope()
}