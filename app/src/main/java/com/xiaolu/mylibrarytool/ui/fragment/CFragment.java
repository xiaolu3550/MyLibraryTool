package com.xiaolu.mylibrarytool.ui.fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;

import com.gyf.immersionbar.ImmersionBar;
import com.xiaolu.mylibrary.base.BaseFragment;
import com.xiaolu.mylibrary.base.BaseMvpActivity;
import com.xiaolu.mylibrary.base.BaseMvpFragment;
import com.xiaolu.mylibrary.mvpbase.BasePresenter;
import com.xiaolu.mylibrary.mvpbase.IPresenter;
import com.xiaolu.mylibrary.mvpbase.IView;
import com.xiaolu.mylibrary.utils.ToolbarHelper;
import com.xiaolu.mylibrarytool.App;
import com.xiaolu.mylibrarytool.R;
import com.xiaolu.mylibrarytool.databinding.TextCFragmentBinding;

import org.jetbrains.annotations.NotNull;

public class CFragment extends BaseMvpFragment<TextCFragmentBinding, IView, IPresenter<IView>> {
    private static CFragment cFragment;
    private Toolbar toolbar;

    public static CFragment getInstance() {
        Bundle bundle = new Bundle();

        if (cFragment == null) {
            cFragment = new CFragment();
        }
        cFragment.setArguments(bundle);
        return cFragment;
    }

    @Override
    protected void initToolbar(ToolbarHelper toolbarHelper) {
        toolbar = toolbarHelper.getToolbar();
        toolbar.setBackgroundColor(App.getInstance().getResources().getColor(R.color.orange));
    }

    @Override
    protected TextCFragmentBinding onCreateViewBinding(@NonNull @NotNull LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup parent) {
        return TextCFragmentBinding.inflate(inflater);
    }

    @Override
    protected int setToolbarLayout() {
        return 0;
    }


    @Override
    public void initParams(Bundle bundle) {

    }

    @Override
    public void initView() {

    }


    @Override
    protected void doBusiness(Activity mActivity) {
        getBinding().tvText.setText("C Fragment");
    }

    @Override
    public void setListener() {

    }

    @Override
    public void initImmersionBar() {
        ImmersionBar.with(this)
                .statusBarDarkFont(true)
                .titleBar(toolbar)
                .init();
    }

    @Override
    public boolean immersionBarEnabled() {
        return true;
    }

    @Override
    public IPresenter<IView> createPresenter() {
        return null;
    }

    @Override
    public IView createView() {
        return null;
    }
}
