package com.xiaolu.mylibrarytool.ui.fragment;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;

import com.gyf.immersionbar.ImmersionBar;
import com.socks.library.KLog;
import com.xiaolu.mylibrary.base.BaseFragment;
import com.xiaolu.mylibrary.base.LazyFragment;
import com.xiaolu.mylibrary.mvpbase.BasePresenter;
import com.xiaolu.mylibrary.utils.ToolbarHelper;
import com.xiaolu.mylibrarytool.R;

import butterknife.BindView;

public class BFragment extends BaseFragment {
    private static BFragment bFragment;
    @BindView(R.id.tv_text)
    TextView tvText;
    private Toolbar toolbar;

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
        toolbar = toolbarHelper.getToolbar();
        toolbar.setBackgroundColor(Color.parseColor("#FFFFFF"));
    }

    @Override
    public BasePresenter initPresenter() {
        return null;
    }


    @Override
    protected int setToolbarLayout() {
        return 0;
    }

    @Override
    protected int setLayoutResourceId() {
        return R.layout.text_fragment;
    }

    @Override
    public void initParams(Bundle bundle) {

    }

    @Override
    public void initView() {

    }


    @Override
    protected void doBusiness(Activity mActivity) {

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
        return false;
    }
}
