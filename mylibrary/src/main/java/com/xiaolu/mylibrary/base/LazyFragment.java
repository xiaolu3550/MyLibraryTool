package com.xiaolu.mylibrary.base;

import android.os.Bundle;

import com.xiaolu.mylibrary.mvpbase.BaseContract;
import com.xiaolu.mylibrary.mvpbase.BasePresenter;
@Deprecated
public abstract class LazyFragment<P extends BasePresenter<V>, V extends BaseContract.BaseView> extends BaseLazyFragment {
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
            // initDate();
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
