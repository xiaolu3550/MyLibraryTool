package com.xiaolu.mylibrarytool.utils;

import android.os.Looper;
import android.view.View;

import java.util.concurrent.atomic.AtomicBoolean;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;

/**
 * ================================================
 *
 * @ProjectName: MyLibraryTool
 * @Package: com.xiaolu.mylibrarytool.utils
 * @ClassName: ViewClickObservable
 * @Description: java类作用描述
 * @Author: xiaol
 * @CreateDate: 2020/10/27 16:15
 * @UpdateUser: xiaol
 * @UpdateDate: 2020/10/27 16:15
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 * ================================================
 */
public class ViewClickObservable extends Observable<Object> {
    private View view;

    public ViewClickObservable(View view) {
        this.view = view;
    }

    @Override
    protected void subscribeActual(Observer<? super Object> observer) {
        MyListener listener = new MyListener(view, observer);
        observer.onSubscribe(listener);
        view.setOnClickListener(listener);
    }

    static final class MyListener implements Disposable, View.OnClickListener {
        private final View view;
        private final Observer<Object> observer;
        private final AtomicBoolean isDisposable = new AtomicBoolean();

        public MyListener(View view, Observer<Object> observer) {
            this.view = view;
            this.observer = observer;
        }

        @Override
        public void onClick(View v) {
            if (!isDisposed()) {
                observer.onNext(v);
            }
        }

        @Override
        public void dispose() {
            if (isDisposable.compareAndSet(false, true)) {
                if (Looper.myLooper() == Looper.getMainLooper()) {
                    //判断是否是在UI线程
                    view.setOnClickListener(null);
                } else {
                    //不在主线程就切换到主线程
                    AndroidSchedulers.mainThread().scheduleDirect(new Runnable() {
                        @Override
                        public void run() {
                            view.setOnClickListener(null);
                        }
                    });
                }
            }
        }

        @Override
        public boolean isDisposed() {
            return isDisposable.get();

        }
    }
}
