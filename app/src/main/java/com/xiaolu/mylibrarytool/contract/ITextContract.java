package com.xiaolu.mylibrarytool.contract;

import com.xiaolu.mylibrary.mvpbase.IModel;
import com.xiaolu.mylibrary.mvpbase.IPresenter;
import com.xiaolu.mylibrary.mvpbase.IView;
import com.xiaolu.mylibrarytool.bean.BaseObjectBean;
import com.xiaolu.mylibrarytool.bean.LoginBean;

import io.reactivex.Observable;

/**
 * @author xiaol
 * @describe
 * @date 2020/10/28  15:01
 * - generate by MvpAutoCodePlus plugin.
 */

public interface ITextContract {
    interface View extends IView {
        void loginSuccess(String e);

        void loginError(String e);
    }

    interface Presenter extends IPresenter<View> {
        void onClick(String username, String password);

        void login(String login, String password, String loginType, String verifyCode);
    }

    interface Model extends IModel {
        Observable<BaseObjectBean<LoginBean>> login(String loginName, String password, String loginType, String verifyCode);
    }
}
