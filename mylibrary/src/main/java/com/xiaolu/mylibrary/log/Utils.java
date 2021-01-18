package com.xiaolu.mylibrary.log;

import android.text.TextUtils;
import android.util.Log;

/**
 * ================================================
 *
 * @ProjectName: MyLibraryTool
 * @Package: com.xiaolu.mylibrary.log
 * @ClassName: Utils
 * @Description: java类作用描述
 * @Author: xiaol
 * @CreateDate: 2021/1/16 17:25
 * @UpdateUser: xiaol
 * @UpdateDate: 2021/1/16 17:25
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 * ================================================
 */
public class Utils {
    public static boolean isEmpty(String line) {
        return TextUtils.isEmpty(line) || line.equals("\n") || line.equals("\t") || TextUtils.isEmpty(line.trim());
    }

    public static void printLine(String tag, boolean isTop) {
        if (isTop) {
            Log.d(tag, "╔═══════════════════════════════════════════════════════════════════════════════════════");
        } else {
            Log.d(tag, "╚═══════════════════════════════════════════════════════════════════════════════════════");
        }
    }
}
