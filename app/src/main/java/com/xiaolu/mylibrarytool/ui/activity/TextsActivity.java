package com.xiaolu.mylibrarytool.ui.activity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.gyf.immersionbar.ImmersionBar;
import com.trello.rxlifecycle2.LifecycleTransformer;
import com.xiaolu.mylibrary.base.BaseMvpActivity;
import com.xiaolu.mylibrary.utils.ToolbarHelper;
import com.xiaolu.mylibrarytool.R;
import com.xiaolu.mylibrarytool.contract.ITextContract;
import com.xiaolu.mylibrarytool.presenter.TextPresenter;
import com.xiaolu.mylibrarytool.utils.RxUtils;

import butterknife.BindView;

/**
 * @author xiaol
 * @describe
 * @date 2020/10/28  15:01
 * - generate by MvpAutoCodePlus plugin.
 */

public class TextsActivity extends BaseMvpActivity<ITextContract.View, ITextContract.Presenter> implements ITextContract.View {
    @BindView(R.id.et_password)
    EditText password;
    @BindView(R.id.et_name)
    EditText name;
    @BindView(R.id.bt_login)
    Button login;

    @Override
    protected void initToolbar(ToolbarHelper toolbarHelper, ImmersionBar immersionBar) {

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
        return R.layout.text_activity;
    }

    @Override
    public int setToolbarLayout() {
        return 0;
    }

    @Override
    public void initView(View view) {

    }

    @Override
    public void setListener() {
        RxUtils.setOnClickListeners(1, view -> {
            String username = name.getText().toString().trim();
            String passwords = password.getText().toString().trim();
            mPresenter.onClick(username, passwords);
        }, login);
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
    public ITextContract.Presenter createPresenter() {
        return new TextPresenter();
    }

    @Override
    public ITextContract.View createView() {
        return this;
    }

    @Override
    public void loginSuccess(String e) {
        showBottomToast(e);
    }

    @Override
    public void loginError(String e) {
        showBottomToast(e);
    }
}

