package com.xiaolu.mylibrarytool.presenter;

import com.xiaolu.mylibrary.mvpbase.BasePresenter;
import com.xiaolu.mylibrary.net.RxScheduler;
import com.xiaolu.mylibrarytool.bean.BaseObjectBean;
import com.xiaolu.mylibrarytool.bean.GetVerifyCodeBean;
import com.xiaolu.mylibrarytool.bean.LoginBean;
import com.xiaolu.mylibrarytool.bean.RegisterBean;
import com.xiaolu.mylibrarytool.contract.DemoContract;
import com.xiaolu.mylibrarytool.model.DemoModel;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class DemoPresenter extends BasePresenter<DemoContract.View> implements DemoContract.Presenter {
    private BaseObjectBean<GetVerifyCodeBean> mGetVerifyCodeBeanBaseObjectBean;
    private BaseObjectBean<LoginBean> mLoginBeanBaseObjectBean;
    private DemoContract.Model demoModel;

    public DemoPresenter() {
        demoModel = new DemoModel();
    }

    @Override
    public void getVerifyCode(String phoneNumber, String businessType) {
        getView().onLoad();
        demoModel.getVerifyCode(phoneNumber, businessType)
                .compose(RxScheduler.<BaseObjectBean<GetVerifyCodeBean>>Flo_io_main(1, TimeUnit.SECONDS))
                .compose(getView().<BaseObjectBean<GetVerifyCodeBean>>bindToLifecycleS())
                .subscribe(new Observer<BaseObjectBean<GetVerifyCodeBean>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(BaseObjectBean<GetVerifyCodeBean> getVerifyCodeBeanBaseObjectBean) {
                        mGetVerifyCodeBeanBaseObjectBean = getVerifyCodeBeanBaseObjectBean;
                    }

                    @Override
                    public void onError(Throwable e) {
                        getView().onDisMiss();
                    }

                    @Override
                    public void onComplete() {
                        if (getView() != null) {
                            if (mGetVerifyCodeBeanBaseObjectBean.getCode().equals("0")) {
                                getView().getVerifyCodeSuccess(mGetVerifyCodeBeanBaseObjectBean);
                            } else {
                                getView().onError(mGetVerifyCodeBeanBaseObjectBean.getMsg());
                            }
                        }
                        getView().onDisMiss();
                    }
                });
    }

    @Override
    public void login(String login, String password, String loginType, String verifyCode) {
        getView().onLoad();
        demoModel.login(login, password, loginType, verifyCode)
                .compose(RxScheduler.<BaseObjectBean<LoginBean>>Flo_io_main(1, TimeUnit.SECONDS))
                .compose(getView().<BaseObjectBean<LoginBean>>bindToLifecycleS())
                .subscribe(new Observer<BaseObjectBean<LoginBean>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(BaseObjectBean<LoginBean> loginBeanBaseObjectBean) {
                        mLoginBeanBaseObjectBean = loginBeanBaseObjectBean;
                    }

                    @Override
                    public void onError(Throwable e) {
                        getView().onDisMiss();
                    }

                    @Override
                    public void onComplete() {
                        if (getView() != null) {
                            if (mLoginBeanBaseObjectBean.getCode().equals("0")) {
                                getView().loginSuccess(mLoginBeanBaseObjectBean);
                            } else {
                                getView().onError(mLoginBeanBaseObjectBean.getMsg());
                            }
                        }
                        getView().onDisMiss();
                    }
                });
    }

    @Override
    public void register(String phoneNumber, String verifyCode, String password, String confirmPassword, String loginType) {
        getView().onLoad();
        demoModel.register(phoneNumber, verifyCode, password, confirmPassword, loginType)
                .compose(RxScheduler.<BaseObjectBean<RegisterBean>>Flo_io_io(1, TimeUnit.SECONDS))
                .flatMap((registerBeanBaseObjectBean) -> {
                    RegisterBean data = registerBeanBaseObjectBean.getData();
                    String loginName = data.getLoginName();
                    String password1 = data.getPassword();
                    return demoModel.login(loginName, password, loginType, "");

                }).compose(RxScheduler.<BaseObjectBean<LoginBean>>Flo_io_main(1, TimeUnit.SECONDS))
                .compose(getView().<BaseObjectBean<LoginBean>>bindToLifecycleS())
                .subscribe(new Observer<BaseObjectBean<LoginBean>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(BaseObjectBean<LoginBean> loginBeanBaseObjectBean) {
                        mLoginBeanBaseObjectBean = loginBeanBaseObjectBean;
                    }

                    @Override
                    public void onError(Throwable e) {
                        getView().onDisMiss();
                    }

                    @Override
                    public void onComplete() {
                        if (getView() != null) {
                            if (mLoginBeanBaseObjectBean.getCode().equals("0")) {
                                getView().loginSuccess(mLoginBeanBaseObjectBean);
                            } else {
                                getView().onError(mLoginBeanBaseObjectBean.getMsg());
                            }
                        }
                        getView().onDisMiss();
                    }
                });
    }
}
