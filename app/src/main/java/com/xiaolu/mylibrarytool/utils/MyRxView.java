package com.xiaolu.mylibrarytool.utils;

import android.view.View;

import io.reactivex.Observable;

/**
 * ================================================
 *
 * @ProjectName: MyLibraryTool
 * @Package: com.xiaolu.mylibrarytool.utils
 * @ClassName: MyRxView
 * @Description: java类作用描述
 * @Author: xiaol
 * @CreateDate: 2020/10/27 16:21
 * @UpdateUser: xiaol
 * @UpdateDate: 2020/10/27 16:21
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 * ================================================
 */
public class MyRxView {
    /**
     * 把按钮点击事件  转化成 Observable<>形式
     */
    public static Observable<Object> clicks(View view) {
        return new ViewClickObservable(view);
    }
}
