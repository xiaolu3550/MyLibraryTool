package com.xiaolu.mylibrarytool.presenter;

import com.xiaolu.mylibrary.mvpbase.IBasePresenter;
import com.xiaolu.mylibrary.net.RxObserver;
import com.xiaolu.mylibrary.net.RxScheduler;
import com.xiaolu.mylibrarytool.bean.BaseObjectBean;
import com.xiaolu.mylibrarytool.bean.GetVerifyCodeBean;
import com.xiaolu.mylibrarytool.bean.LoginBean;
import com.xiaolu.mylibrarytool.bean.RegisterBean;
import com.xiaolu.mylibrarytool.contract.DemoContract;
import com.xiaolu.mylibrarytool.model.DemoModel;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

public class DemoPresenter extends IBasePresenter<DemoContract.View,DemoContract.Model> implements DemoContract.Presenter {
    private BaseObjectBean<GetVerifyCodeBean> mGetVerifyCodeBeanBaseObjectBean;
    private BaseObjectBean<LoginBean> mLoginBeanBaseObjectBean;
    private DemoModel demoModel;

    @Override
    protected DemoContract.Model createModel() {
        return new DemoModel();
    }
}
