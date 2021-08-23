package com.xiaolu.mylibrarytool.ui.activity;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import androidx.fragment.app.Fragment;

import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.gyf.immersionbar.ImmersionBar;
import com.xiaolu.mylibrary.base.BaseMvpActivity;
import com.xiaolu.mylibrary.mvpbase.IPresenter;
import com.xiaolu.mylibrary.mvpbase.IView;
import com.xiaolu.mylibrary.utils.ToolbarHelper;
import com.xiaolu.mylibrarytool.bean.TextTabEntity;
import com.xiaolu.mylibrarytool.databinding.ActivityTextBinding;
import com.xiaolu.mylibrarytool.ui.MyPagerAdapter;
import com.xiaolu.mylibrarytool.ui.fragment.AFragment;
import com.xiaolu.mylibrarytool.ui.fragment.BFragment;
import com.xiaolu.mylibrarytool.ui.fragment.CFragment;

import java.util.ArrayList;

public class TextActivity extends BaseMvpActivity<ActivityTextBinding, IView, IPresenter<IView>> {

    private String[] title = new String[]{"测试一", "测试二", "测试三"};
    private ArrayList<Fragment> fragments = new ArrayList<>();
    private ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();


    @Override
    protected ActivityTextBinding onCreateViewBinding(LayoutInflater layoutInflater) {
        return ActivityTextBinding.inflate(layoutInflater);
    }

    @Override
    protected void initToolbar(ToolbarHelper toolbarHelper, ImmersionBar immersionBar) {

    }

    private void initFrmaget() {
        fragments.add(AFragment.getInstance());
        fragments.add(BFragment.getInstance());
        fragments.add(CFragment.getInstance());
        getBinding().vp.setAdapter(new MyPagerAdapter(getSupportFragmentManager(), title, fragments));
        getBinding().tl.setViewPager(getBinding().vp, title);
        for (int i = 0; i < title.length; i++) {
            mTabEntities.add(new TextTabEntity(title[i]));
        }
    }

    @Override
    public void initParms(Bundle parms) {

    }


    @Override
    public int setToolbarLayout() {
        return 0;
    }

    @Override
    public void initView(View view) {
        initFrmaget();
    }

    @Override
    public void setListener() {
        getBinding().tl.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                getBinding().vp.setCurrentItem(position);

            }

            @Override
            public void onTabReselect(int position) {

            }
        });
        getBinding().vp.setCurrentItem(0);
    }

    @Override
    public void widgetClick(View v) {

    }

    @Override
    public void doBusiness(Context mContext) {

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
