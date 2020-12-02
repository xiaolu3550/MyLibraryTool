package com.xiaolu.mylibrary.net;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * ================================================
 *
 * @ProjectName: MyLibraryTool
 * @Package: com.xiaolu.mylibrary.net
 * @ClassName: RxApiManager
 * @Description: 取消网络请求
 * @Author: xiaol
 * @CreateDate: 2020/11/3 15:53
 * @UpdateUser: xiaol
 * @UpdateDate: 2020/11/3 15:53
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 * ================================================
 */
public class RxManager {
    private static RxManager instance = null;
    private Map<String, CompositeDisposable> map;

    public static RxManager getInstance() {
        if (instance == null) {
            synchronized (RxManager.class) {
                if (instance == null) {
                    instance = new RxManager();
                }
            }
        }
        return instance;
    }

    private RxManager() {
        map = new HashMap<>();
    }

    public void add(String key, Disposable disposable) {
        Set<String> keySet = map.keySet();
        if (keySet.contains(key)) {
            CompositeDisposable compositeDisposable = map.get(key);
            compositeDisposable.add(disposable);
        } else {
            CompositeDisposable compositeDisposable = new CompositeDisposable();
            compositeDisposable.add(disposable);
            map.put(key, compositeDisposable);
        }
    }

    public void clear(String key) {
        Set<String> keySet = map.keySet();
        if (keySet.contains(key)) {
            CompositeDisposable compositeDisposable = map.get(key);
            compositeDisposable.clear();
            map.remove(key);
        }
    }
}
