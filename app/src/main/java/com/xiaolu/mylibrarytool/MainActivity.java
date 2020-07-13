package com.xiaolu.mylibrarytool;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import com.xiaolu.mylibrary.base.BaseActivity;
import com.xiaolu.mylibrary.utils.ToolbarHelper;
import com.trello.rxlifecycle2.LifecycleTransformer;
import com.xiaolu.mylibrarytool.bean.BaseObjectBean;
import com.xiaolu.mylibrarytool.bean.GetVerifyCodeBean;
import com.xiaolu.mylibrarytool.bean.LoginBean;
import com.xiaolu.mylibrarytool.contract.DemoContract;
import com.xiaolu.mylibrarytool.presenter.DemoPresenter;

import butterknife.OnClick;

public class MainActivity extends BaseActivity<DemoPresenter, DemoContract.View> implements DemoContract.View {
    @Override
    public DemoPresenter initPresenter() {
        return new DemoPresenter();
    }

    @Override
    protected void initToolbar(ToolbarHelper toolbarHelper) {

    }

    @Override
    public void initParms(Bundle parms) {

    }

    @Override
    public View bindView() {
        return null;
    }

    @Override
    public int bindLayout() {
        return R.layout.activity_main;
    }

    @Override
    public void initView(View view) {

    }

    @Override
    public void setListener() {

    }

    @Override
    public void widgetClick(View v) {

    }

    @Override
    public void doBusiness(Context mContext) {

    }

    @Override
    public void getVerifyCodeSuccess(BaseObjectBean<GetVerifyCodeBean> getVerifyCodeBeanBaseObjectBean) {
        String verifyCode = getVerifyCodeBeanBaseObjectBean.getData().getVerifyCode();
        showBottomToast(verifyCode);
    }

    @Override
    public void loginSuccess(BaseObjectBean<LoginBean> loginBeanBaseObjectBean) {
        showBottomToast("登入成功");
    }

    @Override
    public void onError(String message) {
        showBottomToast(message);
    }

    @Override
    public void onLoad() {

    }

    @Override
    public void onDisMiss() {

    }

    @Override
    public void onEmpty() {

    }

    @Override
    public Context getContext() {
        return null;
    }

    @Override
    public <T> LifecycleTransformer<T> bindToLifecycleS() {
        return bindToLifecycle();
    }

    @OnClick({R.id.btn_getVerifyCode, R.id.btn_login})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_getVerifyCode:
                presenter.getVerifyCode("15636808536","2");
                break;
            case R.id.btn_login:
                presenter.login("15636808536", "s123456", "2", "");
                break;
        }
    }
}
