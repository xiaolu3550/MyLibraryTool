package com.xiaolu.mylibrarytool.api

import com.xiaolu.mylibrarytool.bean.*
import io.reactivex.Observable
import okhttp3.RequestBody
import retrofit2.http.Body
import retrofit2.http.POST

interface RetrofitService {
    /**
     * 登入
     */
    @POST("mall/user/without/login")
    suspend fun login(@Body requestBody: RequestBody?): BaseObjectBean<LoginBean>

    @POST("mall/homePage/without/getBanner")
    suspend fun getBanner(): BaseListBean<BannerBean>
}