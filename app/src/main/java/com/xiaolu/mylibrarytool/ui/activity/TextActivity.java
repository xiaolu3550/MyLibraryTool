package com.xiaolu.mylibrarytool.ui.activity;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import androidx.fragment.app.Fragment;

import com.flyco.tablayout.SlidingTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.gyf.immersionbar.ImmersionBar;
import com.socks.library.KLog;
import com.xiaolu.mylibrary.base.BaseActivity;
import com.xiaolu.mylibrary.mvpbase.BasePresenter;
import com.xiaolu.mylibrary.utils.MMKV_Utils;
import com.xiaolu.mylibrary.utils.ToolbarHelper;
import com.xiaolu.mylibrarytool.R;
import com.xiaolu.mylibrarytool.bean.LoginBean;
import com.xiaolu.mylibrarytool.bean.TextTabEntity;
import com.xiaolu.mylibrarytool.ui.MyPagerAdapter;
import com.xiaolu.mylibrarytool.ui.NoScrollViewPager;
import com.xiaolu.mylibrarytool.ui.fragment.AFragment;
import com.xiaolu.mylibrarytool.ui.fragment.BFragment;
import com.xiaolu.mylibrarytool.ui.fragment.CFragment;

import java.util.ArrayList;

import butterknife.BindView;

public class TextActivity extends BaseActivity {
    @BindView(R.id.tl)
    SlidingTabLayout tl;
    @BindView(R.id.vp)
    NoScrollViewPager vp;
    private String[] title = new String[]{"测试一", "测试二", "测试三"};
    private ArrayList<Fragment> fragments = new ArrayList<>();
    private ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();

    @Override
    public BasePresenter initPresenter() {
        return null;
    }

    @Override
    protected void initToolbar(ToolbarHelper toolbarHelper, ImmersionBar immersionBar) {

    }

    private void initFrmaget() {
        fragments.add(AFragment.getInstance());
        fragments.add(BFragment.getInstance());
        fragments.add(CFragment.getInstance());
        vp.setAdapter(new MyPagerAdapter(getSupportFragmentManager(), title, fragments));
        tl.setViewPager(vp, title);
        for (int i = 0; i < title.length; i++) {
            mTabEntities.add(new TextTabEntity(title[i]));
        }
    }

    @Override
    public void initParms(Bundle parms) {

    }

    @Override
    public View bindView() {
        return null;
    }

    @Override
    public int bindLayout() {
        return R.layout.activity_text;
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
        tl.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                vp.setCurrentItem(position);

            }

            @Override
            public void onTabReselect(int position) {

            }
        });
        vp.setCurrentItem(0);
    }

    @Override
    public void widgetClick(View v) {

    }

    @Override
    public void doBusiness(Context mContext) {

    }

}
