package com.xiaolu.mylibrarytool.ui.activity;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import com.gyf.immersionbar.ImmersionBar;
import com.trello.rxlifecycle3.LifecycleTransformer;
import com.xiaolu.mylibrary.base.BaseMvpActivity;
import com.xiaolu.mylibrary.utils.ToolbarHelper;
import com.xiaolu.mylibrarytool.contract.ITextContract;
import com.xiaolu.mylibrarytool.databinding.TextActivityBinding;
import com.xiaolu.mylibrarytool.presenter.TextPresenter;

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

    }

    @Override
    public void widgetClick(View v) {

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
}

