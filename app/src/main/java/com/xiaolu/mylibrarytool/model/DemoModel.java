package com.xiaolu.mylibrarytool.model;

import com.jundu.mylibrary.net.RequestBodyUtils;
import com.xiaolu.mylibrarytool.App;
import com.xiaolu.mylibrarytool.bean.BaseObjectBean;
import com.xiaolu.mylibrarytool.bean.GetVerifyCodeBean;
import com.xiaolu.mylibrarytool.bean.LoginBean;
import com.xiaolu.mylibrarytool.contract.DemoContract;

import java.util.Map;
import java.util.TreeMap;

import io.reactivex.Observable;

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
}
