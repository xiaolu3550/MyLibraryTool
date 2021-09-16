package com.xiaolu.mylibrarytool.contract;

import com.xiaolu.mylibrary.mvpbase.BaseContract;
import com.xiaolu.mylibrarytool.bean.BaseListBean;
import com.xiaolu.mylibrarytool.bean.SearchMonthParkListBean;

import io.reactivex.Observable;

/**
 * ================================================
 *
 * @ProjectName: MyLibraryTool
 * @Package: com.xiaolu.mylibrarytool.contract
 * @ClassName: SearchParkListContract
 * @Description: java类作用描述
 * @Author: xiaol
 * @CreateDate: 2020/8/18 13:35
 * @UpdateUser: xiaol
 * @UpdateDate: 2020/8/18 13:35
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 * ================================================
 */
public interface SearchParkListContract extends BaseContract {

    interface SearchParkListModel extends BaseModel {

    }

    interface SearchParkListView extends BaseView {
        void searchParkListSuccess(BaseListBean<SearchMonthParkListBean> searchMonthParkListBeanBaseObjectBean);
    }

    interface SearchParkListPresenter extends BasePresenter {
        void searchParkList(String name, String longitude, String latitude);
    }
}
