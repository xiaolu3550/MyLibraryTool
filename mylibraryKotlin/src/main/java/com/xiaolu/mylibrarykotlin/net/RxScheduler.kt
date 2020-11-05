package com.xiaolu.mylibrarykotlin.net

import io.reactivex.ObservableTransformer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit

/**
 * description：RXjava 线程调度
 */
object RxScheduler {
    /**
     * 统一线程处理
     *
     * @param <T> 指定的泛型类型
     * @return FlowableTransformer
    </T> */
    fun <T> Flo_io_main(timeOut: Int, unit: TimeUnit?): ObservableTransformer<T, T> {
        return ObservableTransformer { upstream ->
            upstream.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .debounce(timeOut.toLong(), unit)
        }
    }

    fun <T> Flo_io_io(timeOut: Int, unit: TimeUnit?): ObservableTransformer<T, T> {
        return ObservableTransformer { upstream ->
            upstream.subscribeOn(Schedulers.io())
                    .observeOn(Schedulers.io())
                    .debounce(timeOut.toLong(), unit)
        }
    }

    /**
     * 统一线程处理
     *
     * @param <T> 指定的泛型类型
     * @return ObservableTransformer
    </T> */
    fun <T> Obs_io_main(timeOut: Int, unit: TimeUnit?): ObservableTransformer<T, T> {
        return ObservableTransformer { upstream ->
            upstream.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .debounce(timeOut.toLong(), unit)
        }
    }
}