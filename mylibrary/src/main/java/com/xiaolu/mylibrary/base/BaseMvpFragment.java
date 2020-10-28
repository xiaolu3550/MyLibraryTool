package com.xiaolu.mylibrary.base;

import android.os.Bundle;

import com.xiaolu.mylibrary.mvpbase.BaseContract;
import com.xiaolu.mylibrary.mvpbase.BasePresenter;
import com.xiaolu.mylibrary.mvpbase.IPresenter;
import com.xiaolu.mylibrary.mvpbase.IView;
import com.xiaolu.mylibrary.mvpbase.MvpCallback;


/**
 * @author: zhaol
 * @createdOn: 2018/5/17 16:19
 * @description: $desc$
 */
public abstract class BaseMvpFragment<V extends IView, P extends IPresenter<V>> extends RXBaseFragment
        implements MvpCallback<V, P> {
    protected P mPresenter;
    protected V mView;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mView = createView();
        if (getPresenter() == null) {
            mPresenter = createPresenter();
            try {
                mPresenter.attachView((V) this);
                initDate();
            } catch (Exception e) {
                new ClassCastException(this.toString() + "实现BasePresenter或者BaseView子类接口");
            }
        }
    }

    @Override
    public P getPresenter() {
        return mPresenter;
    }

    @Override
    public void setPresenter(P presenter) {
        this.mPresenter = presenter;
    }

    @Override
    public V getMvpView() {
        return mView;
    }

    @Override
    public void setMvpView(V view) {
        this.mView = view;
    }

    @Override
    public void onDestroy() {
        if (mPresenter != null) {
            setMvpView(null);
            setPresenter(null);
        }
        super.onDestroy();
    }

    @Override
    public void onDestroyView() {
        if (mPresenter != null) {
            setMvpView(null);
            setPresenter(null);
        }
        super.onDestroyView();
    }

    @Override
    public void onResume() {
        if (mPresenter != null) {
            mPresenter.attachView((V) this);
        } else {
            mPresenter = createPresenter();
        }
        super.onResume();
    }
}