package com.xiaolu.mylibrarytool.ui.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;

import com.gyf.immersionbar.ImmersionBar;
import com.trello.rxlifecycle3.LifecycleTransformer;
import com.xiaolu.mylibrary.base.BaseFragment;
import com.xiaolu.mylibrary.base.BaseMvpActivity;
import com.xiaolu.mylibrary.base.BaseMvpFragment;
import com.xiaolu.mylibrary.load.callback.BaseCallBack;
import com.xiaolu.mylibrary.mvpbase.IPresenter;
import com.xiaolu.mylibrary.mvpbase.IView;
import com.xiaolu.mylibrary.utils.ToolbarHelper;
import com.xiaolu.mylibrarytool.App;
import com.xiaolu.mylibrarytool.R;
import com.xiaolu.mylibrarytool.bean.BaseListBean;
import com.xiaolu.mylibrarytool.bean.SearchMonthParkListBean;
import com.xiaolu.mylibrarytool.callback.EmptyCallBack;
import com.xiaolu.mylibrarytool.callback.ErrorCallBack;
import com.xiaolu.mylibrarytool.callback.LoadCallBack;
import com.xiaolu.mylibrarytool.contract.SearchParkListContract;
import com.xiaolu.mylibrarytool.databinding.TextAFragmentBinding;
import com.xiaolu.mylibrarytool.presenter.SearchParkListPresenter;

import org.jetbrains.annotations.NotNull;

public class AFragment extends BaseMvpFragment<TextAFragmentBinding, IView, IPresenter<IView>> {
    private static AFragment aFragment;

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
        toolbarHelper.setBackground(App.getInstance().getResources().getColor(R.color.blue));
    }

    @Override
    protected TextAFragmentBinding onCreateViewBinding(@NonNull @NotNull LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup parent) {
        return TextAFragmentBinding.inflate(inflater);
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
    public void initDate() {

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
