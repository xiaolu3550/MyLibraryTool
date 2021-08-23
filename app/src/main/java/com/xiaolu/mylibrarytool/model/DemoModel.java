package com.xiaolu.mylibrarytool.model;

import com.xiaolu.mylibrary.net.RequestBodyUtils;
import com.xiaolu.mylibrarytool.App;
import com.xiaolu.mylibrarytool.bean.BaseObjectBean;
import com.xiaolu.mylibrarytool.bean.GetVerifyCodeBean;
import com.xiaolu.mylibrarytool.bean.LoginBean;
import com.xiaolu.mylibrarytool.bean.RegisterBean;
import com.xiaolu.mylibrarytool.contract.DemoContract;

import org.jetbrains.annotations.NotNull;

import java.util.Map;
import java.util.TreeMap;

import io.reactivex.Observable;
import kotlinx.coroutines.CoroutineScope;

public class DemoModel implements DemoContract.Model {

    private Map<String, Object> map;

    @Override
    public Observable<BaseObjectBean<GetVerifyCodeBean>> getVerifyCode(String phoneNumber, String businessType) {
        map = new TreeMap<>();
        map.put("phoneNumber", phoneNumber);
        map.put("businessType", businessType);
        return App.getClient().getVerifyCode(RequestBodyUtils.setRequestBody_JSON(map));
    }

    @Override
    public Observable<BaseObjectBean<LoginBean>> login(String loginName, String password, String loginType, String verifyCode) {
        map = new TreeMap<>();
        map.put("loginName", loginName);
        map.put("password", password);
        map.put("loginType", loginType);
        map.put("verifyCode", verifyCode);
        return App.getClient().login(RequestBodyUtils.setRequestBody_JSON(map));
    }

    @Override
    public Observable<BaseObjectBean<RegisterBean>> register(String phoneNumber, String verifyCode, String password, String confirmPassword, String loginType) {
        map = new TreeMap<>();
        map.put("phoneNumber", phoneNumber);
        map.put("verifyCode", verifyCode);
        map.put("password", password);
        map.put("confirmPassword", confirmPassword);
        return App.getClient().register(RequestBodyUtils.setRequestBody_JSON(map));
    }

    @NotNull
    @Override
    public CoroutineScope getMainScope() {
        return null;
    }

    @Override
    public void setMainScope(@NotNull CoroutineScope mainScope) {

    }

    @Override
    public void onDestroy() {

    }
}
