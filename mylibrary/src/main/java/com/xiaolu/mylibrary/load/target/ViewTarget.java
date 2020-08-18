package com.xiaolu.mylibrary.load.target;

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
 * @ClassName: ViewTarget
 * @Description: java类作用描述
 * @Author: xiaol
 * @CreateDate: 2020/8/18 11:25
 * @UpdateUser: xiaol
 * @UpdateDate: 2020/8/18 11:25
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 * ================================================
 */
public class ViewTarget implements ITarget{
    @Override
    public boolean equals(Object target) {
        return target instanceof View;
    }

    @Override
    public LoadLayout replaceView(Object target, BaseCallBack.OnReloadListener onReloadListener) {
        View oldContent = (android.view.View) target;
        ViewGroup contentParent = (ViewGroup) (oldContent.getParent());
        int childIndex = 0;
        int childCount = contentParent == null ? 0 : contentParent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            if (contentParent.getChildAt(i) == oldContent) {
                childIndex = i;
                break;
            }
        }
        if (contentParent != null) {
            contentParent.removeView(oldContent);
        }
        ViewGroup.LayoutParams oldLayoutParams = oldContent.getLayoutParams();
        LoadLayout loadLayout = new LoadLayout(oldContent.getContext(), onReloadListener);
        loadLayout.setupSuccessLayout(new SuccessCallBack(oldContent, oldContent.getContext(),onReloadListener));
        if (contentParent != null) {
            contentParent.addView(loadLayout, childIndex, oldLayoutParams);
        }
        return loadLayout;
    }
}
