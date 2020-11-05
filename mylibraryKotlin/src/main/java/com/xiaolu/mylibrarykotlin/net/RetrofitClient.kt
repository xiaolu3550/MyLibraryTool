package com.xiaolu.mylibrarykotlin.net

import com.google.gson.Gson
import com.socks.library.KLog
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import java.nio.charset.Charset
import java.util.*
import java.util.concurrent.TimeUnit

/**
 * ================================================
 * @ProjectName:    MyLibraryTool
 * @Package:        com.xiaolu.mylibrarykotlin.net
 * @ClassName:      RetrofitClient
 * @Description:     java类作用描述
 * @Author:          xiaol
 * @CreateDate:     2020/11/4 15:24
 * @UpdateUser:     xiaol
 * @UpdateDate:     2020/11/4 15:24
 * @UpdateRemark:   更新说明
 * @Version:        1.0
 * ================================================
 */
class RetrofitClient private constructor() {
    private var baseUrl: String? = null

    companion object {
        @Volatile
        var instance: RetrofitClient? = null
            get() {
                if (instance == null) {
                    synchronized(RetrofitClient::class.java) {
                        if (instance == null) {
                            instance = RetrofitClient()
                        }
                    }
                }
                return instance
            }
    }

    fun getInstance(): RetrofitClient? {
        if (instance == null) {
            synchronized(RetrofitClient::class.java) {
                if (instance == null) {
                    instance = RetrofitClient()
                }
            }
        }
        return instance
    }

    fun setBaseUrl(baseUrl: String): RetrofitClient {
        this.baseUrl = baseUrl
        return this
    }

    fun <T> getApi(clazz: Class<T>): T {
        //初始化一个client,不然retrofit会自己默认添加一个
        val client = OkHttpClient()
                .newBuilder()
                .connectTimeout(60, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS) //设置拦截器
                .addInterceptor(mLoggingInterceptor)
                .build()

        val retrofit = Retrofit.Builder()
                .client(client) //设置网络请求的Url地址
                .baseUrl(baseUrl) //设置数据解析器
                .addConverterFactory(LenientGsonConverterFactory.create(Gson())) //设置网络请求适配器，使其支持RxJava与RxAndroid
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
        return retrofit.create(clazz)
    }

    private val mLoggingInterceptor = Interceptor { chain ->
        val request = chain.request()
        val t1 = System.nanoTime()
        //            KLog.i(String.format("Sending request: %s on %s%n%s", request.url(), chain.connection()
//            , request.headers(), request.body().toString()));
        val response = chain.proceed(request)
        val t2 = System.nanoTime()
        KLog.i(String.format(Locale.getDefault(), "Received response: for %s in %.1fms%n%s",
                response.request().url(), (t2 - t1).toDouble() / 1000000.0, response.headers()))
        val body = response.body()
        val source = body!!.source()
        source.request(9223372036854775807L)
        val buffer = source.buffer()
        var charset = Charset.defaultCharset()
        val contentType = body.contentType()
        if (contentType != null) {
            charset = contentType.charset(charset)
        }
        val bodyString = buffer.clone().readString(charset!!)
        KLog.i(bodyString)
        response
    }
}