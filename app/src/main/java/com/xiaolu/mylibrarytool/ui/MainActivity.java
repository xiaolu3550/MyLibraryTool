package com.xiaolu.mylibrarytool.ui;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.gyf.immersionbar.ImmersionBar;
import com.socks.library.KLog;

import com.trello.rxlifecycle3.LifecycleTransformer;
import com.xiaolu.mylibrary.base.BaseActivity;
import com.xiaolu.mylibrary.dialog.CustomDialog;
import com.xiaolu.mylibrary.rxbus.RegisterRxBus;
import com.xiaolu.mylibrary.rxbus.RxBus;
import com.xiaolu.mylibrary.utils.ToolbarHelper;
import com.xiaolu.mylibrarytool.R;
import com.xiaolu.mylibrarytool.bean.BaseObjectBean;
import com.xiaolu.mylibrarytool.bean.GetVerifyCodeBean;
import com.xiaolu.mylibrarytool.bean.LoginBean;
import com.xiaolu.mylibrarytool.contract.DemoContract;
import com.xiaolu.mylibrarytool.presenter.DemoPresenter;
import com.xiaolu.mylibrarytool.ui.activity.TextActivity;
import com.xiaolu.mylibrarytool.utils.RxUtils;
import com.xiaolu.mylibrarytool.ui.activity.TextsActivity;

import butterknife.BindView;
import butterknife.OnClick;

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
public class MainActivity extends BaseActivity<DemoPresenter, DemoContract.View> implements DemoContract.View {
    @BindView(R.id.et_code)
    EditText etCode;
    @BindView(R.id.ll_load)
    LinearLayout ll_load;
    @BindView(R.id.btn_getVerifyCode)
    Button btn_getVerifyCode;
    @BindView(R.id.btn_login)
    Button btn_login;
    @BindView(R.id.btn_open_place)
    Button btn_open_place;
    private CustomDialog build;

    @Override
    public DemoPresenter initPresenter() {
        return new DemoPresenter();
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
    public View bindView() {
        return null;
    }

    @Override
    public int bindLayout() {
        return R.layout.activity_main;
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
                    KLog.d("1111");
                    presenter.getVerifyCode("15905140019", "1");
                    break;
                case R.id.btn_login:
                    KLog.d("2222");
                    presenter.login("15905140019", "123456", "2", "");
                    break;
                default:
                    break;
            }
        }, btn_getVerifyCode, btn_login);
    }

    @Override
    public void widgetClick(View v) {

    }

    @Override
    public void doBusiness(Context mContext) {
        //btn_open_place.sett
    }

    @RegisterRxBus(TextsActivity.class)
    public void setText(String s) {
        btn_open_place.setText(s);
    }

    @RegisterRxBus(TextActivity.class)
    public void setText1(String s) {
        btn_open_place.setText(s);
    }

    @Override
    public void getVerifyCodeSuccess(BaseObjectBean<GetVerifyCodeBean> getVerifyCodeBeanBaseObjectBean) {
        String verifyCode = getVerifyCodeBeanBaseObjectBean.getData().getVerifyCode();
        etCode.setText(verifyCode);
        showBottomToast(verifyCode);
    }

    @Override
    public void loginSuccess(BaseObjectBean<LoginBean> loginBeanBaseObjectBean) {
        KLog.d(loginBeanBaseObjectBean.toString());
        showBottomToast(loginBeanBaseObjectBean.toString());
    }

    @Override
    public void onError(String message) {
        showBottomToast(message);
    }

    @Override
    public void onLoad() {

    }

    @Override
    public void onDisMiss() {

    }

    @Override
    public void onEmpty() {

    }

    @Override
    public Context getContext() {
        return null;
    }

    @Override
    public <T> LifecycleTransformer<T> bindToLifecycleS() {
        return bindToLifecycle();
    }

    //R.id.btn_getVerifyCode, R.id.btn_login,
    @OnClick({R.id.btn_open, R.id.btn_re, R.id.btn_open_dialog, R.id.btn_open_place})
    public void onViewClicked(View view) {
        switch (view.getId()) {
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
                String code = etCode.getText().toString().trim();
                presenter.register("15905140019", code, "123456", "123456", "2");
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

}
