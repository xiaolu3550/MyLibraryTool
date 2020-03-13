package com.xiaolu.mylibrarytool.contract;

import com.jundu.mylibrary.mvpbase.BaseView;
import com.xiaolu.mylibrarytool.bean.BaseObjectBean;
import com.xiaolu.mylibrarytool.bean.GetVerifyCodeBean;
import com.xiaolu.mylibrarytool.bean.LoginBean;

import io.reactivex.Observable;

public interface DemoContract {
    interface Model {
        Observable<BaseObjectBean<GetVerifyCodeBean>> getVerifyCode(String phoneNumber, String businessType);

        Observable<BaseObjectBean<LoginBean>> login(String loginName, String password, String loginType, String verifyCode);
    }

    interface View extends BaseView {
        void getVerifyCodeSuccess(BaseObjectBean<GetVerifyCodeBean> getVerifyCodeBeanBaseObjectBean);

        void loginSuccess(BaseObjectBean<LoginBean> loginBeanBaseObjectBean);
    }

    interface Presenter {
        void getVerifyCode(String phoneNumber,String businessType);

        void login(String login, String password, String loginType, String verifyCode);
    }
}