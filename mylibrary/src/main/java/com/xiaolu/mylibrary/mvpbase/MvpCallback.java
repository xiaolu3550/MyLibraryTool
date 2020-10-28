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
    /**
     * 创建Presenter  调用在onCreate中
     *
     * @return
     */
    P createPresenter();

    //创建View

    /**
     * 创建View
     *
     * @return
     */
    V createView();

    /**
     * 获取Presenter
     *
     * @return
     */
    P getPresenter();

    /**
     * 设置Presenter
     *
     * @param presenter
     */
    void setPresenter(P presenter);

    /**
     * 获取View
     *
     * @return
     */
    V getMvpView();

    /**
     * 设置View
     *
     * @param view
     */
    void setMvpView(V view);
}
