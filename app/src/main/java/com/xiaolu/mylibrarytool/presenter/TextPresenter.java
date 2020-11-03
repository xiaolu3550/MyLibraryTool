package com.xiaolu.mylibrarytool.presenter;

import android.annotation.SuppressLint;

import com.socks.library.KLog;
import com.xiaolu.mylibrary.mvpbase.IBasePresenter;
import com.xiaolu.mylibrary.net.RxObserver;
import com.xiaolu.mylibrary.net.RxScheduler;
import com.xiaolu.mylibrarytool.bean.BaseObjectBean;
import com.xiaolu.mylibrarytool.bean.LoginBean;
import com.xiaolu.mylibrarytool.contract.ITextContract;
import com.xiaolu.mylibrarytool.model.TextModel;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

/**
 * @author xiaol
 * @describe
 * @date 2020/10/28  15:01
 * - generate by MvpAutoCodePlus plugin.
 */

public class TextPresenter extends IBasePresenter<ITextContract.View, ITextContract.Model> implements ITextContract.Presenter {
    private BaseObjectBean<LoginBean> mLoginBeanBaseObjectBean;

    @Override
    protected ITextContract.Model createModel() {
        return new TextModel();
    }

    @Override
    public void onClick(String username, String password) {
        KLog.d("username : " + username + "password : " + password);
        login(username, password, "2", "");
    }

    @SuppressLint("CheckResult")
    @Override
    public void login(String login, String password, String loginType, String verifyCode) {
        mModel.login(login, password, loginType, verifyCode)
                .compose(RxScheduler.<BaseObjectBean<LoginBean>>Flo_io_main(1, TimeUnit.SECONDS))
                .compose(getView().<BaseObjectBean<LoginBean>>bindToLifecycleS())
                .subscribe(new RxObserver<BaseObjectBean<LoginBean>>(getClass().getName()) {
                    @Override
                    public void onSuccess(@NonNull BaseObjectBean<LoginBean> loginBeanBaseObjectBean) {
                        getView().loginSuccess(mLoginBeanBaseObjectBean.toString());
                    }

                    @Override
                    public void onErrors(@NonNull Throwable e) {
                        getView().loginError(e.getMessage());
                    }
                });
    }
}

