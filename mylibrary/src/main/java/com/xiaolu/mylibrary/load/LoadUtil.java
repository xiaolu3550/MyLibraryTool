package com.xiaolu.mylibrary.load;

import android.os.Looper;

import com.xiaolu.mylibrary.load.target.ITarget;

import java.util.List;

/**
 * ================================================
 *
 * @ProjectName: MyLibraryTool
 * @Package: com.xiaolu.mylibrary.load
 * @ClassName: LoadUtil
 * @Description: java类作用描述
 * @Author: xiaol
 * @CreateDate: 2020/8/18 11:15
 * @UpdateUser: xiaol
 * @UpdateDate: 2020/8/18 11:15
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 * ================================================
 */
public class LoadUtil {
    public static boolean isMainThread() {
        return Looper.myLooper() == Looper.getMainLooper();
    }

    public static ITarget getTargetContext(Object target, List<ITarget> targetContextList) {
        for (ITarget targetContext : targetContextList) {
            if (targetContext.equals(target)) {
                return targetContext;
            }

        }
        throw new IllegalArgumentException("No TargetContext fit it");
    }
}
