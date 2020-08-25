package com.xiaolu.mylibrarytool.ui.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;

import com.gyf.immersionbar.ImmersionBar;
import com.trello.rxlifecycle2.LifecycleTransformer;
import com.xiaolu.mylibrary.base.BaseFragment;
import com.xiaolu.mylibrary.load.callback.BaseCallBack;
import com.xiaolu.mylibrary.utils.ToolbarHelper;
import com.xiaolu.mylibrarytool.App;
import com.xiaolu.mylibrarytool.R;
import com.xiaolu.mylibrarytool.bean.BaseListBean;
import com.xiaolu.mylibrarytool.bean.SearchMonthParkListBean;
import com.xiaolu.mylibrarytool.callback.EmptyCallBack;
import com.xiaolu.mylibrarytool.callback.ErrorCallBack;
import com.xiaolu.mylibrarytool.callback.LoadCallBack;
import com.xiaolu.mylibrarytool.contract.SearchParkListContract;
import com.xiaolu.mylibrarytool.presenter.SearchParkListPresenter;

import butterknife.BindView;

public class AFragment extends BaseFragment<SearchParkListPresenter, SearchParkListContract.SearchParkListView> implements SearchParkListContract.SearchParkListView {
    private static AFragment aFragment;
    @BindView(R.id.tv_text)
    TextView tvText;
    @BindView(R.id.ll_load)
    LinearLayout llLoad;
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
    protected int setToolbarLayout() {
        return 0;
    }

    @Override
    protected int setLayoutResourceId() {
        return R.layout.text_a_fragment;
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
    public SearchParkListPresenter initPresenter() {
        return new SearchParkListPresenter(llLoad, (BaseCallBack.OnReloadListener) v -> {
           initDate();
        });
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
        presenter.searchParkList("", "0", "0");
    }

    @Override
    public boolean immersionBarEnabled() {
        return true;
    }

    @Override
    public void searchParkListSuccess(BaseListBean<SearchMonthParkListBean> searchMonthParkListBeanBaseObjectBean) {
        tvText.setText("A Fragment");
        presenter.getLoadService().showSuccess();
    }

    @Override
    public void onError(String message) {
        presenter.getLoadService().showCallback(ErrorCallBack.class);
    }

    @Override
    public void onLoad() {
        presenter.getLoadService().showCallback(LoadCallBack.class);
    }

    @Override
    public void onDisMiss() {

    }

    @Override
    public void onEmpty() {
        presenter.getLoadService().showCallback(EmptyCallBack.class);
    }

    @Override
    public <T> LifecycleTransformer<T> bindToLifecycleS() {
        return bindToLifecycle();
    }
}
