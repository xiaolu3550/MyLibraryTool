package com.xiaolu.mylibrarytool.ui;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;

import com.socks.library.KLog;
import com.trello.rxlifecycle2.LifecycleTransformer;
import com.xiaolu.mylibrary.base.BaseActivity;
import com.xiaolu.mylibrary.dialog.CustomDialog;
import com.xiaolu.mylibrary.utils.ToolbarHelper;
import com.xiaolu.mylibrarytool.R;
import com.xiaolu.mylibrarytool.bean.BaseObjectBean;
import com.xiaolu.mylibrarytool.bean.GetVerifyCodeBean;
import com.xiaolu.mylibrarytool.bean.LoginBean;
import com.xiaolu.mylibrarytool.contract.DemoContract;
import com.xiaolu.mylibrarytool.presenter.DemoPresenter;
import com.xiaolu.mylibrarytool.ui.activity.TextActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
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
    private CustomDialog build;

    @Override
    public DemoPresenter initPresenter() {
        return new DemoPresenter();
    }

    @Override
    protected void initToolbar(ToolbarHelper toolbarHelper) {
        toolbarHelper.setTitle("标题", R.color.black, 14);
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

    @OnClick({R.id.btn_getVerifyCode, R.id.btn_login, R.id.btn_open, R.id.btn_re, R.id.btn_open_dialog})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_getVerifyCode:
                presenter.getVerifyCode("15905140019", "1");
                break;
            case R.id.btn_login:
                presenter.login("15905140019", "123456", "2", "");
                break;
            case R.id.btn_open:
                skipActivity(TextActivity.class);
                break;
            case R.id.btn_re:
                String code = etCode.getText().toString().trim();
                presenter.register("15905140019", code, "123456", "123456", "2");
                break;
            case R.id.btn_open_dialog:
                CustomDialog.Builder customDialog = new CustomDialog.Builder(mContext);
                build = customDialog.view(R.layout.demo_dialog)
                        .style(R.style.style_dialog)
                        .heightdp(150)
                        .widthdp(250)
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
            default:
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
