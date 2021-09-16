package com.xiaolu.mylibrarytool.ui.activity;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.NonNull;

import com.gyf.immersionbar.ImmersionBar;
import com.trello.rxlifecycle3.LifecycleTransformer;
import com.xiaolu.mylibrary.base.BaseMvpActivity;
import com.xiaolu.mylibrary.log.LogUtil;
import com.xiaolu.mylibrary.utils.ToolbarHelper;
import com.xiaolu.mylibrarytool.R;
import com.xiaolu.mylibrarytool.bean.BannerBean;
import com.xiaolu.mylibrarytool.bean.BaseListBean;
import com.xiaolu.mylibrarytool.bean.BaseObjectBean;
import com.xiaolu.mylibrarytool.bean.LoginBean;
import com.xiaolu.mylibrarytool.bean.LoginRequestBean;
import com.xiaolu.mylibrarytool.contract.ITextContract;
import com.xiaolu.mylibrarytool.databinding.TextActivityBinding;
import com.xiaolu.mylibrarytool.presenter.TextPresenter;

import java.util.List;

/**
 * @author xiaol
 * @describe
 * @date 2020/10/28  15:01
 * - generate by MvpAutoCodePlus plugin.
 */

public class TextsActivity extends BaseMvpActivity<TextActivityBinding, ITextContract.View, ITextContract.Presenter>
        implements ITextContract.View {

    @Override
    protected TextActivityBinding onCreateViewBinding(LayoutInflater layoutInflater) {
        return TextActivityBinding.inflate(layoutInflater);
    }

    @Override
    protected void initToolbar(ToolbarHelper toolbarHelper, ImmersionBar immersionBar) {

    }

    @Override
    public void initParms(Bundle parms) {

    }

    @Override
    public int setToolbarLayout() {
        return 0;
    }

    @Override
    public void initView(View view) {

    }

    @Override
    public void initDate() {
        mPresenter.getBanner();
    }

    @Override
    public void setListener() {
        getBinding().btLogin.setOnClickListener(this);
    }

    @Override
    public void widgetClick(View v) {
        switch (v.getId()) {
            case R.id.bt_login:
                mPresenter.login(new LoginRequestBean("15636808536", "+86", null, "1234"));
                break;
        }
    }

    @Override
    public void doBusiness(Context mContext) {

    }

    @Override
    public <T> LifecycleTransformer<T> bindToLifecycleS() {
        return bindToLifecycle();
    }

    @Override
    public TextPresenter createPresenter() {
        return new TextPresenter();
    }

    @Override
    public ITextContract.View createView() {
        return this;
    }

    @Override
    public void onSuccess(List<BannerBean> any) {
        LogUtil.d(any.toString());
    }

    @Override
    public void onError(@NonNull String string) {
        LogUtil.d(string);
    }

    @Override
    public void loginSuccess(@NonNull LoginBean any) {
        LogUtil.d(any.toString());
    }

    @Override
    public void loginError(@NonNull String string) {
        LogUtil.d(string);
    }
}

