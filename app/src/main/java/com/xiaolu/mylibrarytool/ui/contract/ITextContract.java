package com.xiaolu.mylibrarytool.ui.contract;

import com.xiaolu.mylibrary.mvpbase.IModel;
import com.xiaolu.mylibrary.mvpbase.IPresenter;
import com.xiaolu.mylibrary.mvpbase.IView;

/**
 * @author xiaol
 * @describe
 * @date 2020/10/28  14:36
 * - generate by MvpAutoCodePlus plugin.
 */

public interface ITextContract {
    interface View extends IView {
    }

    interface Presenter extends IPresenter<View> {
    }

    interface Model extends IModel {
    }
}
