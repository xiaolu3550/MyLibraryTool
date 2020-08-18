package com.xiaolu.mylibrarytool.presenter;

import com.xiaolu.mylibrary.load.callback.BaseCallBack;
import com.xiaolu.mylibrary.mvpbase.BasePresenter;
import com.xiaolu.mylibrary.net.RxScheduler;
import com.xiaolu.mylibrarytool.bean.BaseListBean;
import com.xiaolu.mylibrarytool.bean.SearchMonthParkListBean;
import com.xiaolu.mylibrarytool.contract.SearchParkListContract;
import com.xiaolu.mylibrarytool.model.SearchParkListModel;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

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
        searchParkListModel.searchParkList(name, longitude, latitude)
                .compose(RxScheduler.Flo_io_main(0, TimeUnit.SECONDS))
                .compose(getView().bindToLifecycleS())
                .subscribe(new Observer<BaseListBean<SearchMonthParkListBean>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(BaseListBean<SearchMonthParkListBean> searchMonthParkListBean) {
                        mSearchMonthParkListBean = searchMonthParkListBean;
                    }

                    @Override
                    public void onError(Throwable e) {
                        getView().onError(e.getMessage());

                    }

                    @Override
                    public void onComplete() {
                        if (isViewAttached()) {
                            if (mSearchMonthParkListBean.getCode().equals("0")) {
                                getView().searchParkListSuccess(mSearchMonthParkListBean);
                            } else {
                                getView().onError(mSearchMonthParkListBean.getMsg());
                            }
                        }
                    }
                });
    }
}
