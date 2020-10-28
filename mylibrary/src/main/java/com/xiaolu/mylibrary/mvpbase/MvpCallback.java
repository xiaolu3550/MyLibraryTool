package com.xiaolu.mylibrary.mvpbase;

import androidx.lifecycle.LifecycleOwner;

/**
 * ================================================
 *
 * @ProjectName: MyLibraryTool
 * @Package: com.xiaolu.mylibrary.mvpbase
 * @ClassName: MvpCallback
 * @Description: java类作用描述
 * @Author: xiaol
 * @CreateDate: 2020/10/28 13:46
 * @UpdateUser: xiaol
 * @UpdateDate: 2020/10/28 13:46
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 * ================================================
 */
public interface MvpCallback<V extends IView & LifecycleOwner, P extends IPresenter<V>> {
    //创建Presenter  调用在init中
    P createPresenter();

    //创建View
    V createView();

    P getPresenter();

    void setPresenter(P presenter);

    V getMvpView();

    void setMvpView(V view);
}
