package com.xiaolu.mylibrary.net

import com.google.gson.JsonParseException
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.json.JSONException
import retrofit2.HttpException
import java.io.EOFException
import java.io.IOException
import java.net.SocketException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import java.text.ParseException
import javax.net.ssl.SSLHandshakeException

// GlobalScope.launch
fun launchGlobal(
    coroutineScope: CoroutineScope,
    onFailure: (e: Throwable) -> Unit = {},
    onComplete: () -> Unit = {},
    block: suspend CoroutineScope.() -> Unit
) {
    coroutineScope.launch(Dispatchers.Main + CoroutineExceptionHandler { coroutineContext, throwable ->
        onFailure(throwable)
    }) {
        try {
            block.invoke(this)
        } finally {
            onComplete()
        }
    }
}

// 默认的错误解析
fun onError(e: Throwable): Throwable {
    when (e) {
        is SocketTimeoutException, is SocketException, is UnknownHostException -> {
            return Throwable("网络超时或中断，请检查您的网络后重试")
        }
        is SSLHandshakeException -> {
            return Throwable("网络证书验证失败")
        }
        is HttpException -> {
            return Throwable("服务器访问失败，请稍后重试")
        }
        is NullPointerException, is JSONException, is JsonParseException, is ParseException, is EOFException -> {
            return Throwable("数据解析失败")
        }
        is IOException -> {
            return Throwable("数据传输失败")
        }
        is ApiException -> {
            return Throwable(e.message)
        }
        else -> {
            return Throwable("未知错误")
        }
    }
}