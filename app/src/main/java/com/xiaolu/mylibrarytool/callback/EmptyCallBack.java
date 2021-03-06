package com.xiaolu.mylibrarytool.callback;

import com.xiaolu.mylibrary.load.callback.BaseCallBack;
import com.xiaolu.mylibrarytool.R;

/**
 * ================================================
 *
 * @ProjectName: MyLibraryTool
 * @Package: com.xiaolu.mylibrarytool.callback
 * @ClassName: LoadCallBack
 * @Description: java类作用描述
 * @Author: xiaol
 * @CreateDate: 2020/8/18 13:19
 * @UpdateUser: xiaol
 * @UpdateDate: 2020/8/18 13:19
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 * ================================================
 */
public class EmptyCallBack extends BaseCallBack {
    @Override
    protected int onCreateView() {
        return R.layout.empty;
    }
}
