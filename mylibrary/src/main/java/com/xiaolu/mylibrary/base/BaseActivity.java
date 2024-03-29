package com.xiaolu.mylibrary.base;

import android.os.Bundle;

import com.xiaolu.mylibrary.mvpbase.BaseContract;
import com.xiaolu.mylibrary.mvpbase.BasePresenter;
import com.xiaolu.mylibrary.net.RxManager;


/**
 * ================================================
 *
 * @ProjectName: mylibrary
 * @Package: com.xiaolu.mylibrary.base
 * @ClassName: BaseActivity
 * @Description: Activity父类
 * @Author: 赵璐
 * @CreateDate: 2020/7/13 13:49
 * @UpdateUser: 更新者: 赵璐
 * @UpdateDate: 2020/7/13 13:49
 * @UpdateRemark: 更新内容:
 * @Version: 1.0
 * ================================================
 */
@Deprecated
public abstract class BaseActivity<P extends BasePresenter<V>, V extends BaseContract.BaseView> extends RxBaseActivity {
    protected P presenter;

    /**
     * 初始Presenter
     *
     * @return P
     * @method initPresenter
     * @description: 初始Presenter
     * @date: 2020/7/13 13:51
     * @author: 赵璐
     */
    public abstract P initPresenter();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = initPresenter();
        try {
            presenter.attachView((V) this);
            initDate();
            initDate("");
        } catch (Exception e) {
            new ClassCastException(this.toString() + "实现IPresenterView或者IPresenterView子类接口");
        }
    }

    @Override
    protected void onDestroy() {
        if (presenter != null) {
            RxManager.getInstance().clear(presenter.getClass().getName());
        }
        if (presenter != null) {
            presenter.detachView();
        }
        super.onDestroy();
    }

    @Override
    protected void onPause() {
        if (presenter != null) {
            RxManager.getInstance().clear(presenter.getClass().getName());
        }
        super.onPause();
    }

    @Override
    protected void onResume() {
        if (presenter != null) {
            presenter.attachView((V) this);
        } else {
            presenter = initPresenter();
        }
        super.onResume();
    }
}
