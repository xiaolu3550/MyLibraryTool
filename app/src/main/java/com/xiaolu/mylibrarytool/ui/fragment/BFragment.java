package com.xiaolu.mylibrarytool.ui.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.xiaolu.mylibrary.base.BaseFragment;
import com.xiaolu.mylibrary.mvpbase.BasePresenter;
import com.xiaolu.mylibrary.utils.ToolbarHelper;
import com.xiaolu.mylibrarytool.R;

import butterknife.BindView;

public class BFragment extends BaseFragment {
    private static BFragment bFragment;
    @BindView(R.id.tv_text)
    TextView tvText;
    @BindView(R.id.ll_load)
    LinearLayout llLoad;


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
        tvText.setText("B");

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
}
