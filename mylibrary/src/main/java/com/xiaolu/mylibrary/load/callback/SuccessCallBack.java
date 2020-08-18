package com.xiaolu.mylibrary.load.callback;

import android.content.Context;
import android.view.View;

/**
 * ================================================
 *
 * @ProjectName: MyLibraryTool
 * @Package: com.xiaolu.mylibrary.load.callback
 * @ClassName: SuccessCallback
 * @Description: java类作用描述
 * @Author: xiaol
 * @CreateDate: 2020/8/18 11:13
 * @UpdateUser: xiaol
 * @UpdateDate: 2020/8/18 11:13
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 * ================================================
 */
public class SuccessCallBack extends BaseCallBack {
    public SuccessCallBack(View view, Context context, OnReloadListener onReloadListener) {
        super(view, context, onReloadListener);
    }

    @Override
    protected int onCreateView() {
        return 0;
    }

    /**
     * @deprecated Use {@link #showWithCallback(boolean successVisible)} instead.
     */
    public void hide() {
        obtainRootView().setVisibility(View.INVISIBLE);
    }

    public void show() {
        obtainRootView().setVisibility(View.VISIBLE);
    }

    public void showWithCallback(boolean successVisible) {
        obtainRootView().setVisibility(successVisible ? View.VISIBLE : View.INVISIBLE);
    }

}
