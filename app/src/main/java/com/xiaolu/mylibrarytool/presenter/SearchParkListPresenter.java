package com.xiaolu.mylibrarytool.presenter;

import com.xiaolu.mylibrary.load.callback.BaseCallBack;
import com.xiaolu.mylibrary.mvpbase.BasePresenter;
import com.xiaolu.mylibrary.net.RxObserver;
import com.xiaolu.mylibrary.net.RxScheduler;
import com.xiaolu.mylibrarytool.bean.BaseListBean;
import com.xiaolu.mylibrarytool.bean.SearchMonthParkListBean;
import com.xiaolu.mylibrarytool.contract.SearchParkListContract;
import com.xiaolu.mylibrarytool.model.SearchParkListModel;

import java.util.concurrent.TimeUnit;

import io.reactivex.annotations.NonNull;

/**
 * ================================================
 *
 * @ProjectName: MyLibraryTool
 * @Package: com.xiaolu.mylibrarytool.presenter
 * @ClassName: SearchParkListPresenter
 * @Description: java类作用描述
 * @Author: xiaol
 * @CreateDate: 2020/8/18 13:44
 * @UpdateUser: xiaol
 * @UpdateDate: 2020/8/18 13:44
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 * ================================================
 */
public class SearchParkListPresenter extends BasePresenter<SearchParkListContract.SearchParkListView>
        implements SearchParkListContract.SearchParkListPresenter {
    private BaseListBean<SearchMonthParkListBean> mSearchMonthParkListBean;
    private SearchParkListModel searchParkListModel;

    public SearchParkListPresenter(Object o, BaseCallBack.OnReloadListener onReloadListener) {
        searchParkListModel = new SearchParkListModel();
        setLoadService(o, onReloadListener);
    }

    @Override
    public void searchParkList(String name, String longitude, String latitude) {
        getView().onLoad();

    }
}
