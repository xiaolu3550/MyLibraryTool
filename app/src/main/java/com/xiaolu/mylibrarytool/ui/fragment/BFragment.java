package com.xiaolu.mylibrarytool.ui.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.xiaolu.mylibrary.base.BaseFragment;
import com.xiaolu.mylibrary.base.BaseMvpFragment;
import com.xiaolu.mylibrary.mvpbase.BasePresenter;
import com.xiaolu.mylibrary.mvpbase.IPresenter;
import com.xiaolu.mylibrary.mvpbase.IView;
import com.xiaolu.mylibrary.utils.ToolbarHelper;
import com.xiaolu.mylibrarytool.R;
import com.xiaolu.mylibrarytool.databinding.TextFragmentBinding;

import org.jetbrains.annotations.NotNull;

public class BFragment extends BaseMvpFragment<TextFragmentBinding, IView, IPresenter<IView>> {
    private static BFragment bFragment;

    public static BFragment getInstance() {
        Bundle bundle = new Bundle();

        if (bFragment == null) {
            bFragment = new BFragment();
        }
        bFragment.setArguments(bundle);
        return bFragment;
    }

    @Override
    protected void initToolbar(ToolbarHelper toolbarHelper) {

    }

    @Override
    protected TextFragmentBinding onCreateViewBinding(@NonNull @NotNull LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup parent) {
        return TextFragmentBinding.inflate(inflater);
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
       getBinding().tvText.setText("B");

    }

    @Override
    public void setListener() {

    }


    @Override
    public void initImmersionBar() {

    }

    @Override
    public boolean immersionBarEnabled() {
        return false;
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
