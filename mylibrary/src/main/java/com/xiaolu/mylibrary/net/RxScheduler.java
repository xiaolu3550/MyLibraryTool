
package com.xiaolu.mylibrary.net;


import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


/**
 * description：RXjava 线程调度
 */

public class RxScheduler {
    /**
     * 统一线程处理
     *
     * @param <T> 指定的泛型类型
     * @return FlowableTransformer
     */

    public static <T> ObservableTransformer<T, T> floIoMain(int timeOut, TimeUnit unit) {
        return upstream -> upstream.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .debounce(timeOut, unit);
    }

    public static <T> ObservableTransformer<T, T> floIoIo(int timeOut, TimeUnit unit) {
        return upstream -> upstream.subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .debounce(timeOut, unit);
    }


    /**
     * 统一线程处理
     *
     * @param <T> 指定的泛型类型
     * @return ObservableTransformer
     */

    public static <T> ObservableTransformer<T, T> obsIoMain(int timeOut, TimeUnit unit) {
        return upstream -> upstream.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .debounce(timeOut, unit);
    }

}
