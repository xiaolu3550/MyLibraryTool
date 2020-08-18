package com.xiaolu.mylibrarytool.model;

import com.xiaolu.mylibrary.net.RequestBodyUtils;
import com.xiaolu.mylibrarytool.App;
import com.xiaolu.mylibrarytool.bean.BaseListBean;
import com.xiaolu.mylibrarytool.bean.SearchMonthParkListBean;
import com.xiaolu.mylibrarytool.contract.SearchParkListContract;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observable;

/**
 * ================================================
 *
 * @ProjectName: MyLibraryTool
 * @Package: com.xiaolu.mylibrarytool.model
 * @ClassName: SearchParkListModel
 * @Description: java类作用描述
 * @Author: xiaol
 * @CreateDate: 2020/8/18 13:39
 * @UpdateUser: xiaol
 * @UpdateDate: 2020/8/18 13:39
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 * ================================================
 */
public class SearchParkListModel implements SearchParkListContract.SearchParkListModel {
    @Override
    public Observable<BaseListBean<SearchMonthParkListBean>> searchParkList(String name, String longitude, String latitude) {
        Map<String, Object> map = new HashMap<>();
        map.put("name", name);
        map.put("longitude", longitude);
        map.put("latitude", latitude);
        return App.getClient().searchParkList(RequestBodyUtils.setRequestBody_JSON(map));
    }
}
