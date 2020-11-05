package com.xiaolu.mylibrarykotlin.utils

import com.xiaolu.mylibrarykotlin.eventbean.MessageEvent
import org.greenrobot.eventbus.EventBus

object EventBusUtil {
    @JvmStatic
    fun register(subscriber: Any?) {
        EventBus.getDefault().register(subscriber)
    }

    @JvmStatic
    fun unregister(subscriber: Any?) {
        EventBus.getDefault().unregister(subscriber)
    }

    @JvmStatic
    fun sendEvent(event: MessageEvent<*>?) {
        EventBus.getDefault().post(event)
    }

    @JvmStatic
    fun sendStickyEvent(event: MessageEvent<*>?) {
        EventBus.getDefault().postSticky(event)
    }
}