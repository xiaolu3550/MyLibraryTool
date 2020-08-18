package com.xiaolu.mylibrarytool.api;

import com.xiaolu.mylibrarytool.bean.BaseListBean;
import com.xiaolu.mylibrarytool.bean.BaseObjectBean;
import com.xiaolu.mylibrarytool.bean.GetVerifyCodeBean;
import com.xiaolu.mylibrarytool.bean.LoginBean;
import com.xiaolu.mylibrarytool.bean.RegisterBean;
import com.xiaolu.mylibrarytool.bean.SearchMonthParkListBean;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface RetrofitService {
    /**
     * 根据手机号获取验证码
     *
     * @param requestBody
     * @return
     */
    @POST("mobileUserManager/getVerifyCode")
    Observable<BaseObjectBean<GetVerifyCodeBean>> getVerifyCode(@Body RequestBody requestBody);

    /**
     * 根据手机号或账户名称、密码登录
     *
     * @param requestBody
     * @return
     */
    @POST("mobileUserManager/loginCheck")
    Observable<BaseObjectBean<LoginBean>> login(@Body RequestBody requestBody);

    /**
     * 根据手机号或账户名称、密码登录
     *
     * @param requestBody
     * @return
     */
    @POST("mobileUserManager/register")
    Observable<BaseObjectBean<RegisterBean>> register(@Body RequestBody requestBody);

    /**
     * 根据手机号或账户名称、密码登录
     *
     * @param requestBody
     * @return
     */
    @POST("monthCard/seachParkList")
    Observable<BaseListBean<SearchMonthParkListBean>> searchParkList(@Body RequestBody requestBody);
}
