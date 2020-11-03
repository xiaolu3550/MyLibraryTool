package com.xiaolu.mylibrary.rxbus;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * ================================================
 *
 * @ProjectName: MyLibraryTool
 * @Package: com.xiaolu.mylibrary.utils
 * @ClassName: RegisterRxBus
 * @Description: java类作用描述
 * @Author: xiaol
 * @CreateDate: 2020/11/3 14:52
 * @UpdateUser: xiaol
 * @UpdateDate: 2020/11/3 14:52
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 * ================================================
 */

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface RegisterRxBus {
    Class value();
}
