package com.xiaolu.mylibrarytool.ui.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import com.xiaolu.mylibrary.base.LazyFragment;
import com.xiaolu.mylibrary.mvpbase.BasePresenter;
import com.xiaolu.mylibrarytool.R;

import butterknife.BindView;

public class AFragment extends LazyFragment {
    private static AFragment aFragment;
    @BindView(R.id.tv_text)
    TextView tvText;

    public static AFragment getInstance() {
        Bundle bundle = new Bundle();

        if (aFragment == null) {
            aFragment = new AFragment();
        }
        aFragment.setArguments(bundle);
        return aFragment;
    }

    @Override
    public void loadDataStart() {
        mLoadDataFinished = true;
    }

    @Override
    protected void initParams(Bundle bundle) {

    }

    @Override
    protected void doBusiness(Activity mActivity) {
        tvText.setText("A Fragment");
    }

    @Override
    protected int getLayoutId() {
        return R.layout.text_fragment;
    }

    @Override
    public BasePresenter initPresenter() {
        return null;
    }
}
