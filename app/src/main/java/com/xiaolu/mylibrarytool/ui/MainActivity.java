package com.xiaolu.mylibrarytool.ui;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import com.gyf.immersionbar.ImmersionBar;
import com.trello.rxlifecycle3.LifecycleTransformer;
import com.xiaolu.mylibrary.base.BaseMvpActivity;
import com.xiaolu.mylibrary.dialog.CustomDialog;
import com.xiaolu.mylibrary.log.LogUtil;
import com.xiaolu.mylibrary.rxbus.RegisterRxBus;
import com.xiaolu.mylibrary.rxbus.RxBus;
import com.xiaolu.mylibrary.utils.ToolbarHelper;
import com.xiaolu.mylibrarytool.R;
import com.xiaolu.mylibrarytool.bean.BaseObjectBean;
import com.xiaolu.mylibrarytool.bean.GetVerifyCodeBean;
import com.xiaolu.mylibrarytool.bean.LoginBean;
import com.xiaolu.mylibrarytool.contract.DemoContract;
import com.xiaolu.mylibrarytool.databinding.ActivityMainBinding;
import com.xiaolu.mylibrarytool.presenter.DemoPresenter;
import com.xiaolu.mylibrarytool.ui.activity.TextActivity;
import com.xiaolu.mylibrarytool.ui.activity.TextsActivity;
import com.xiaolu.mylibrarytool.utils.RxUtils;

/**
 * ================================================
 *
 * @ProjectName:
 * @Package: com.xiaolu.mylibrarytool.ui
 * @ClassName: MainActivity
 * @Description: java类作用描述
 * @Author: xiaol
 * @CreateDate: 2020/7/10 16:37
 * @UpdateUser: 更新者:
 * @UpdateDate: 2020/7/10 16:37
 * @UpdateRemark: 更新内容:
 * @Version: 1.0
 * ================================================
 */
public class MainActivity extends BaseMvpActivity<ActivityMainBinding
        , DemoContract.View, DemoPresenter> implements DemoContract.View {
    private CustomDialog build;

    @Override
    protected ActivityMainBinding onCreateViewBinding(LayoutInflater layoutInflater) {
        return ActivityMainBinding.inflate(layoutInflater);
    }

    @Override
    protected void initToolbar(ToolbarHelper toolbarHelper, ImmersionBar immersionBar) {
        //  toolbarHelper.setTitle("标题", R.color.black, 14);
        //   toolbarHelper.setBackground(R.color.red);
        toolbarHelper.getToolbar().setNavigationIcon(R.mipmap.back_left);
       /* immersionBar
                .statusBarView(R.id.top_view)
                .navigationBarColor(R.color.red)
                .statusBarDarkFont(false)
                .fullScreen(true)
                .init();*/
        //  super.initToolbar(toolbarHelper);
        immersionBar
                //.navigationBarColor(R.color.red)
                //.titleBar(toolbarHelper.getToolbar())
                .statusBarDarkFont(true)
                //  .reset()
                .init();

    }

    @Override
    public void initParms(Bundle parms) {

    }

    @Override
    public int setToolbarLayout() {
        return 0;
        //return R.id.toolbar;
    }

    @Override
    public void initView(View view) {
        RxBus.getInstance().register(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        RxBus.getInstance().unRegister(this);
    }

    @Override
    public void setListener() {
        RxUtils.setOnClickListeners(1, view -> {
            View view1 = (View) view;
            switch ((view1).getId()) {
                case R.id.btn_getVerifyCode:
                    LogUtil.d("1111");
                    mPresenter.getVerifyCode("15905140019", "1");
                    break;
                case R.id.btn_login:
                    LogUtil.d("2222");
                    mPresenter.login("15905140019", "123456", "2", "");
                    break;
                default:
                    break;
            }
        }, getBinding().btnGetVerifyCode, getBinding().btnLogin);
        getBinding().btnOpenPlace.setOnClickListener(this);
    }

    @Override
    public void widgetClick(View v) {
        switch (v.getId()) {
            //  case R.id.btn_getVerifyCode:
            //     presenter.getVerifyCode("15905140019", "1");
            //     break;
            //    case R.id.btn_login:
            //        presenter.login("15905140019", "123456", "2", "");
            //        break;
            case R.id.btn_open:
                skipActivity(TextActivity.class);
                break;
            case R.id.btn_re:
                String code = getBinding().etCode.getText().toString().trim();
                mPresenter.register("15905140019", code, "123456", "123456", "2");
                break;
            case R.id.btn_open_dialog:
                CustomDialog.Builder customDialog = new CustomDialog.Builder(mContext, R.layout.demo_dialog);
                Button btn_cancel = customDialog.getView(R.id.btn_cancel);
                Button btn_commit = customDialog.getView(R.id.btn_commit);
                btn_cancel.setText("132");
                btn_commit.setText("456");
                build = customDialog
                        .style(R.style.style_dialog)
                        .heightDp(150)
                        .widthDp(250)
                        .addViewOnclick(R.id.btn_cancel, new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                build.dismiss();
                            }
                        }).addViewOnclick(R.id.btn_commit, new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                build.dismiss();
                            }
                        })
                        .build();
                build.show(MainActivity.this.getSupportFragmentManager(), "CustomDialog");
                //  build.dismiss();
                break;
            case R.id.btn_open_place:
                skipActivity(TextsActivity.class);
                break;
            default:
                break;
        }
    }

    @Override
    public void doBusiness(Context mContext) {

    }

    @RegisterRxBus(TextsActivity.class)
    public void setText(String s) {
        getBinding().btnOpenPlace.setText(s);
    }

    @RegisterRxBus(TextActivity.class)
    public void setText1(String s) {
        getBinding().btnOpenPlace.setText(s);
    }

    @Override
    public void getVerifyCodeSuccess(BaseObjectBean<GetVerifyCodeBean> getVerifyCodeBeanBaseObjectBean) {
        String verifyCode = getVerifyCodeBeanBaseObjectBean.getData().getVerifyCode();
        getBinding().etCode.setText(verifyCode);
        showBottomToast(verifyCode);
    }

    @Override
    public void loginSuccess(BaseObjectBean<LoginBean> loginBeanBaseObjectBean) {
        LogUtil.d(loginBeanBaseObjectBean.toString());
        showBottomToast(loginBeanBaseObjectBean.toString());
    }

    @Override
    public <T> LifecycleTransformer<T> bindToLifecycleS() {
        return bindToLifecycle();
    }

    @Override
    public DemoPresenter createPresenter() {
        return new DemoPresenter();
    }

    @Override
    public DemoContract.View createView() {
        return this;
    }
}
