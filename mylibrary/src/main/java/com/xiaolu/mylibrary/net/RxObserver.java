package com.xiaolu.mylibrary.net;

import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

/**
 * ================================================
 *
 * @ProjectName: MyLibraryTool
 * @Package: com.xiaolu.mylibrary.net
 * @ClassName: RxObserver
 * @Description: 自定义rxjavaObserver
 * @Author: xiaol
 * @CreateDate: 2020/11/3 16:49
 * @UpdateUser: xiaol
 * @UpdateDate: 2020/11/3 16:49
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 * ================================================
 */
public abstract class RxObserver<T> implements Observer<T> {
    private RxManager rxApiManager;
    private String mKey;
    public RxObserver(String key) {
        this.mKey = key;
        rxApiManager = RxManager.getInstance();
    }

    @Override
    public void onSubscribe(@NonNull Disposable d) {
        rxApiManager.add(mKey, d);
    }

    @Override
    public void onNext(@NonNull T t) {
        onSuccess(t);
    }

    @Override
    public void onError(@NonNull Throwable e) {
        onErrors(e);
    }

    @Override
    public void onComplete() {

    }

    public abstract void onSuccess(@NonNull T t);

    public abstract void onErrors(@NonNull Throwable e);
}
