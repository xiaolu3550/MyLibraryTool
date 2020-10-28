package com.xiaolu.mylibrarytool.utils;

import android.annotation.SuppressLint;
import android.view.View;

import org.jetbrains.annotations.NotNull;

import java.util.concurrent.TimeUnit;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;

/**
 * ================================================
 *
 * @ProjectName: MyLibraryTool
 * @Package: com.xiaolu.mylibrarytool.utils
 * @ClassName: RxUtils
 * @Description: java类作用描述
 * @Author: xiaol
 * @CreateDate: 2020/10/27 16:22
 * @UpdateUser: xiaol
 * @UpdateDate: 2020/10/27 16:22
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 * ================================================
 */
public class RxUtils {
    /**
     * 防止重复点击
     *
     * @param seconds 间隔时间
     * @param action  回调方法
     * @param target  点击view
     */
    @SuppressLint("CheckResult")
    public static void setOnClickListeners(int seconds, final Action1<Object> action, View target) {
        MyRxView.clicks(target)
                .throttleFirst(seconds, TimeUnit.SECONDS)
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(o -> action.onClick(o));
    }


    /**
     * 防止重复点击
     *
     * @param seconds 间隔时间
     * @param action  回调方法
     * @param target  点击view
     */
    @SuppressLint("CheckResult")
    public static void setOnClickListeners(int seconds, final Action1<Object> action, @NotNull View... target) {
        for (View view : target) {
            MyRxView.clicks(view)
                    .throttleFirst(seconds, TimeUnit.SECONDS)
                    .subscribeOn(AndroidSchedulers.mainThread())
                    .subscribe(o -> action.onClick(o));
        }
    }

    public interface Action1<T> {
        void onClick(T view);
    }
}
