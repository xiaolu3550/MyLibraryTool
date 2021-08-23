package com.xiaolu.mylibrarytool.ui.activity;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import com.gyf.immersionbar.ImmersionBar;
import com.xiaolu.mylibrary.base.BaseMvpActivity;
import com.xiaolu.mylibrary.mvpbase.IPresenter;
import com.xiaolu.mylibrary.mvpbase.IView;
import com.xiaolu.mylibrary.utils.ToolbarHelper;
import com.xiaolu.mylibrarytool.databinding.PlaceHolderActivityBinding;


/**
 * ================================================
 *
 * @ProjectName: MyLibraryTool
 * @Package: com.xiaolu.mylibrarytool.ui.activity
 * @ClassName: PlaceHolderActivity
 * @Description: java类作用描述
 * @Author: xiaol
 * @CreateDate: 2020/8/19 16:33
 * @UpdateUser: xiaol
 * @UpdateDate: 2020/8/19 16:33
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 * ================================================
 */
public class PlaceHolderActivity extends BaseMvpActivity<PlaceHolderActivityBinding, IView, IPresenter<IView>> {


    @Override
    protected PlaceHolderActivityBinding onCreateViewBinding(LayoutInflater layoutInflater) {
        return PlaceHolderActivityBinding.inflate(layoutInflater);
    }

    @Override
    protected void initToolbar(ToolbarHelper toolbarHelper, ImmersionBar immersionBar) {

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

    }

    @Override
    public void setListener() {

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
