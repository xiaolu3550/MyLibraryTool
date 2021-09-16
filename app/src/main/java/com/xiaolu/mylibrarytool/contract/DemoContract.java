package com.xiaolu.mylibrarytool.contract;

import com.xiaolu.mylibrary.mvpbase.IModel;
import com.xiaolu.mylibrary.mvpbase.IPresenter;
import com.xiaolu.mylibrary.mvpbase.IView;
import com.xiaolu.mylibrarytool.bean.BaseObjectBean;
import com.xiaolu.mylibrarytool.bean.GetVerifyCodeBean;
import com.xiaolu.mylibrarytool.bean.LoginBean;
import com.xiaolu.mylibrarytool.bean.RegisterBean;

import io.reactivex.Observable;

public interface DemoContract {
    interface Model extends IModel {

    }

    interface View extends IView {
        void getVerifyCodeSuccess(BaseObjectBean<GetVerifyCodeBean> getVerifyCodeBeanBaseObjectBean);

        void loginSuccess(BaseObjectBean<LoginBean> loginBeanBaseObjectBean);
    }

    interface Presenter extends IPresenter<View> {

    }
}
