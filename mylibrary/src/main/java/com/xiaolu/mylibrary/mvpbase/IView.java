package com.xiaolu.mylibrary.mvpbase;

import androidx.lifecycle.LifecycleOwner;

import com.trello.rxlifecycle3.LifecycleTransformer;


/**
 * ================================================
 *
 * @ProjectName: MyLibraryTool
 * @Package: com.xiaolu.mylibrary.mvpbase
 * @ClassName: IView
 * @Description: java类作用描述
 * @Author: xiaol
 * @CreateDate: 2020/10/28 13:35
 * @UpdateUser: xiaol
 * @UpdateDate: 2020/10/28 13:35
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 * ================================================
 */
public interface IView extends LifecycleOwner {
    /**
     * rxJavaLife
     *
     * @param <T>
     * @return
     */
    <T> LifecycleTransformer<T> bindToLifecycleS();
}
