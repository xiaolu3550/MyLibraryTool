package com.xiaolu.mylibrarytool.ui.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;

import com.gyf.immersionbar.ImmersionBar;
import com.xiaolu.mylibrary.base.BaseFragment;
import com.xiaolu.mylibrary.base.LazyFragment;
import com.xiaolu.mylibrary.mvpbase.BasePresenter;
import com.xiaolu.mylibrary.utils.ToolbarHelper;
import com.xiaolu.mylibrarytool.R;

import butterknife.BindView;

public class AFragment extends BaseFragment {
    private static AFragment aFragment;
    @BindView(R.id.tv_text)
    TextView tvText;
    private Toolbar toolbar;

    public static AFragment getInstance() {
        Bundle bundle = new Bundle();

        if (aFragment == null) {
            aFragment = new AFragment();
        }
        aFragment.setArguments(bundle);
        return aFragment;
    }

    @Override
    protected void initToolbar(ToolbarHelper toolbarHelper) {
        toolbar = toolbarHelper.getToolbar();
        toolbarHelper.setTitle("标题", R.color.black, 14);
        toolbarHelper.setBackground(R.color.blue);
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
        tvText.setText("A Fragment");
    }

    @Override
    public void setListener() {

    }

    @Override
    public BasePresenter initPresenter() {
        return null;
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
