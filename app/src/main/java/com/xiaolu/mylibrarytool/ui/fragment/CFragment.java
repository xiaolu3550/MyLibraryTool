package com.xiaolu.mylibrarytool.ui.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

import com.socks.library.KLog;
import com.xiaolu.mylibrary.base.BaseFragment;
import com.xiaolu.mylibrary.base.LazyFragment;
import com.xiaolu.mylibrary.mvpbase.BasePresenter;
import com.xiaolu.mylibrary.utils.ToolbarHelper;
import com.xiaolu.mylibrarytool.R;

import butterknife.BindView;

public class CFragment extends LazyFragment {
    private static CFragment cFragment;
    @BindView(R.id.tv_text)
    TextView tvText;

    public static CFragment getInstance() {
        Bundle bundle = new Bundle();

        if (cFragment == null) {
            cFragment = new CFragment();
        }
        cFragment.setArguments(bundle);
        return cFragment;
    }

    @Override
    public BasePresenter initPresenter() {
        return null;
    }

    @Override
    public void loadDataStart() {
        Handler mHandler = new Handler();
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                mLoadDataFinished = true;
                tvText.setText("C Fragment");
            }
        }, 5000);
    }

    @Override
    public void initParams(Bundle bundle) {

    }


    @Override
    protected void doBusiness(Activity mActivity) {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.text_fragment;
    }


}
