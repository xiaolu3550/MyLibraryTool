package com.xiaolu.mylibrarytool.ui.activity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.gyf.immersionbar.ImmersionBar;

import com.xiaolu.mylibrary.base.BaseActivity;
import com.xiaolu.mylibrary.mvpbase.BasePresenter;
import com.xiaolu.mylibrary.utils.ToolbarHelper;
import com.xiaolu.mylibrarytool.R;

import butterknife.BindView;

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
public class PlaceHolderActivity extends BaseActivity {
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.iv_dao_hang)
    ImageView ivDaoHang;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_distance)
    TextView tvDistance;
    @BindView(R.id.tv_address)
    TextView tvAddress;
    @BindView(R.id.ll_go)
    LinearLayout llGo;

    @Override
    public BasePresenter initPresenter() {
        return null;
    }

    @Override
    protected void initToolbar(ToolbarHelper toolbarHelper, ImmersionBar immersionBar) {

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
        return R.layout.place_holder_activity;
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

}
