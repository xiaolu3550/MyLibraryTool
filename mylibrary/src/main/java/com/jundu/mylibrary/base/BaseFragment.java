package com.jundu.mylibrary.base;

import android.os.Bundle;

import com.jundu.mylibrary.mvpbase.BasePresenter;
import com.jundu.mylibrary.mvpbase.BaseView;


/**
 * @author: zhaol
 * @createdOn: 2018/5/17 16:19
 * @description: $desc$
 */
public abstract class BaseFragment<P extends BasePresenter<V>, V extends BaseView> extends RXBaseFragment {
    protected P presenter;

    //实例化Presenter
    public abstract P initPresenter();

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        presenter = initPresenter();
        try {
            presenter.attachView((V) this);
            //checkActivityAttached();
            initDate();
        } catch (Exception e) {
            new ClassCastException(this.toString() + "实现BasePresenter或者BaseView子类接口");
        }
    }

    @Override
    public void onDestroy() {
        if (presenter != null) {
            presenter.detachView();
        }
        super.onDestroy();
    }

    @Override
    public void onResume() {
        if (presenter != null) {
            presenter.attachView((V) this);
        } else {
            presenter = initPresenter();
        }
        super.onResume();
    }
}
