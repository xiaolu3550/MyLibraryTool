package com.jundu.mylibrary.base;

import android.os.Bundle;

import com.jundu.mylibrary.mvpbase.BasePresenter;
import com.jundu.mylibrary.mvpbase.BaseView;


/**
 * @author: zhaol
 * @createdOn: 2018/5/17 15:28
 * @description: $desc$
 */
public abstract class BaseActivity<P extends BasePresenter<V>, V extends BaseView> extends RXBaseActivity {
    protected P presenter;

    //实例化Presenter
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
            presenter.detachView();
        }
        super.onDestroy();
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
