package com.xiaolu.mylibrary.mvpbase;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.OnLifecycleEvent;

/**
 * ================================================
 *
 * @ProjectName: MyLibraryTool
 * @Package: com.xiaolu.mylibrary.mvpbase
 * @ClassName: IPresenter
 * @Description: java类作用描述
 * @Author: xiaol
 * @CreateDate: 2020/10/28 13:41
 * @UpdateUser: xiaol
 * @UpdateDate: 2020/10/28 13:41
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 * ================================================
 */
public interface IPresenter<V extends IView & LifecycleOwner> extends LifecycleObserver {
    /**
     * 创建view时调用  调用在initView 之后
     *
     * @param view
     */
    void attachView(V view);

    /**
     * view销毁时调用释放资源
     */
    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    void detachView();

}
