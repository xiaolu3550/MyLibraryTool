package com.xiaolu.mylibrarytool.api

import com.xiaolu.mylibrarytool.bean.*
import io.reactivex.Observable
import okhttp3.RequestBody
import retrofit2.http.Body
import retrofit2.http.POST

interface RetrofitService {
    /**
     * 根据手机号获取验证码
     *
     * @param requestBody
     * @return
     */
    @POST("mobileUserManager/getVerifyCode")
    fun getVerifyCode(@Body requestBody: RequestBody?): Observable<BaseObjectBean<GetVerifyCodeBean?>?>?

    /**
     * 根据手机号或账户名称、密码登录
     *
     * @param requestBody
     * @return
     */
    @POST("mobileUserManager/loginCheck")
    fun login(@Body requestBody: RequestBody?): Observable<BaseObjectBean<LoginBean?>?>?

    /**
     * 根据手机号或账户名称、密码登录
     *
     * @param requestBody
     * @return
     */
    @POST("mobileUserManager/register")
    fun register(@Body requestBody: RequestBody?): Observable<BaseObjectBean<RegisterBean?>?>?

    /**
     * 根据手机号或账户名称、密码登录
     *
     * @param requestBody
     * @return
     */
    @POST("monthCard/seachParkList")
    fun searchParkList(@Body requestBody: RequestBody?): Observable<BaseListBean<SearchMonthParkListBean?>?>?

    @POST("mall/homePage/without/getBanner")
    suspend fun getBanner(): Any
}