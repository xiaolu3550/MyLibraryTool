package com.xiaolu.mylibrary.load.target;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;

import com.xiaolu.mylibrary.load.callback.BaseCallBack;
import com.xiaolu.mylibrary.load.callback.SuccessCallBack;
import com.xiaolu.mylibrary.load.core.LoadLayout;

/**
 * ================================================
 *
 * @ProjectName: MyLibraryTool
 * @Package: com.xiaolu.mylibrary.load.target
 * @ClassName: ActivityTarget
 * @Description: java类作用描述
 * @Author: xiaol
 * @CreateDate: 2020/8/18 11:24
 * @UpdateUser: xiaol
 * @UpdateDate: 2020/8/18 11:24
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 * ================================================
 */
public class ActivityTarget implements ITarget {

    @Override
    public boolean equals(Object target) {
        return target instanceof Activity;
    }

    @Override
    public LoadLayout replaceView(Object target, BaseCallBack.OnReloadListener onReloadListener) {
        Activity activity = (Activity) target;
        ViewGroup contentParent = activity.findViewById(android.R.id.content);
        int childIndex = 0;
        View oldContent = contentParent.getChildAt(childIndex);
        contentParent.removeView(oldContent);

        ViewGroup.LayoutParams oldLayoutParams = oldContent.getLayoutParams();
        LoadLayout loadLayout = new LoadLayout(activity, onReloadListener);
        loadLayout.setupSuccessLayout(new SuccessCallBack(oldContent, activity,
                onReloadListener));
        contentParent.addView(loadLayout, childIndex, oldLayoutParams);
        return loadLayout;
    }
}
