package com.xiaolu.mylibrarytool.model;

import com.xiaolu.mylibrary.net.RequestBodyUtils;
import com.xiaolu.mylibrarytool.App;
import com.xiaolu.mylibrarytool.bean.BaseObjectBean;
import com.xiaolu.mylibrarytool.bean.LoginBean;
import com.xiaolu.mylibrarytool.contract.ITextContract;

import java.util.TreeMap;

import io.reactivex.Observable;

/**
 * @author xiaol
 * @describe
 * @date 2020/10/28  15:01
 * - generate by MvpAutoCodePlus plugin.
 */

public class TextModel implements ITextContract.Model {

    private TreeMap<String, Object> map;

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

