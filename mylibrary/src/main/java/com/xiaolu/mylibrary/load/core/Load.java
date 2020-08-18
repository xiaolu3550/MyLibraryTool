package com.xiaolu.mylibrary.load.core;

import androidx.annotation.NonNull;

import com.xiaolu.mylibrary.load.LoadUtil;
import com.xiaolu.mylibrary.load.callback.BaseCallBack;
import com.xiaolu.mylibrary.load.target.ActivityTarget;
import com.xiaolu.mylibrary.load.target.ITarget;
import com.xiaolu.mylibrary.load.target.ViewTarget;

import java.util.ArrayList;
import java.util.List;

/**
 * ================================================
 *
 * @ProjectName: MyLibraryTool
 * @Package: com.xiaolu.mylibrary.load.core
 * @ClassName: Load
 * @Description: java类作用描述
 * @Author: xiaol
 * @CreateDate: 2020/8/18 11:20
 * @UpdateUser: xiaol
 * @UpdateDate: 2020/8/18 11:20
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 * ================================================
 */
public class Load {
    private static volatile Load loadSir;
    private Builder builder;

    public static Load getDefault() {
        if (loadSir == null) {
            synchronized (Load.class) {
                if (loadSir == null) {
                    loadSir = new Load();
                }
            }
        }
        return loadSir;
    }

    private Load() {
        this.builder = new Builder();
    }

    private void setBuilder(@NonNull Builder builder) {
        this.builder = builder;
    }

    private Load(Builder builder) {
        this.builder = builder;
    }

    public LoadService register(@NonNull Object target) {
        return register(target, null, null);
    }

    public LoadService register(Object target, BaseCallBack.OnReloadListener onReloadListener) {
        return register(target, onReloadListener, null);
    }

    public <T> LoadService register(Object target, BaseCallBack.OnReloadListener onReloadListener, Convertor<T>
            convertor) {
        ITarget targetContext = LoadUtil.getTargetContext(target, builder.getTargetContextList());
        LoadLayout loadLayout = targetContext.replaceView(target, onReloadListener);
        return new LoadService<>(convertor, loadLayout, builder);
    }

    public static Builder beginBuilder() {
        return new Builder();
    }

    public static class Builder {
        private List<BaseCallBack> callbacks = new ArrayList<>();
        private List<ITarget> targetContextList = new ArrayList<>();
        private Class<? extends BaseCallBack> defaultCallback;

        {
            targetContextList.add(new ActivityTarget());
            targetContextList.add(new ViewTarget());
        }

        public Builder addCallback(@NonNull BaseCallBack callback) {
            callbacks.add(callback);
            return this;
        }

        /**
         * @param targetContext
         * @return Builder
         * @since 1.3.8
         */
        public Builder addTargetContext(ITarget targetContext) {
            targetContextList.add(targetContext);
            return this;
        }

        public List<ITarget> getTargetContextList() {
            return targetContextList;
        }

        public Builder setDefaultCallback(@NonNull Class<? extends BaseCallBack> defaultCallback) {
            this.defaultCallback = defaultCallback;
            return this;
        }

        List<BaseCallBack> getCallbacks() {
            return callbacks;
        }

        Class<? extends BaseCallBack> getDefaultCallback() {
            return defaultCallback;
        }

        public void commit() {
            getDefault().setBuilder(this);
        }

        public Load build() {
            return new Load(this);
        }

    }
}
