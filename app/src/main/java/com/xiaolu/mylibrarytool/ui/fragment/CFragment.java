package com.xiaolu.mylibrarytool.ui.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;

import com.gyf.immersionbar.ImmersionBar;
import com.xiaolu.mylibrary.base.BaseFragment;
import com.xiaolu.mylibrary.mvpbase.BasePresenter;
import com.xiaolu.mylibrary.utils.ToolbarHelper;
import com.xiaolu.mylibrarytool.App;
import com.xiaolu.mylibrarytool.R;

import butterknife.BindView;

public class CFragment extends BaseFragment {
    private static CFragment cFragment;
    @BindView(R.id.tv_text)
    TextView tvText;
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
    public BasePresenter initPresenter() {
        return null;
    }


    @Override
    protected int setToolbarLayout() {
        return 0;
    }

    @Override
    protected int setLayoutResourceId() {
        return R.layout.text_c_fragment;
    }

    @Override
    public void initParams(Bundle bundle) {

    }

    @Override
    public void initView() {

    }


    @Override
    protected void doBusiness(Activity mActivity) {
        tvText.setText("C Fragment");
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
}
