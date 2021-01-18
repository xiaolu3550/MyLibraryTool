package com.xiaolu.mylibrary.base;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.trello.rxlifecycle3.components.support.RxFragment;
import com.xiaolu.mylibrary.log.LogUtil;

import butterknife.ButterKnife;

public abstract class BaseLazyFragment extends RxFragment {
    public final String TAG = getClass().getSimpleName();
    /**
     * 当做Context去使用, MainActivity
     */
    public Activity mActivity;

    public boolean mHaveLoadData; // 表示是否已经请求过数据

    public boolean mLoadDataFinished; // 表示数据是否已经请求完毕
    private View mRootView;

    // 表示开始加载数据, 但不表示数据加载已经完成
    public abstract void loadDataStart();

    // 表示找控件完成, 给控件们设置数据不会报空指针了
    public boolean mViewInflateFinished;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LogUtil.d("onCreate", TAG + "-->onCreate()");
        mActivity = getActivity();//获取fragment所依赖的activity的对象
        Bundle bundle = getArguments();
        initParams(bundle);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        LogUtil.d("onActivityCreated", TAG + "-->onActivityCreated()");
        doBusiness(mActivity);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        LogUtil.d("onCreateView", TAG + "-->onCreateView()");
        if (mRootView != null) {
            return mRootView;
        }
        mRootView = inflater.inflate(getLayoutId(), container, false);
        ButterKnife.bind(this, mRootView);
        mViewInflateFinished = true;
        return mRootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        LogUtil.d("onViewCreated", TAG + "-->onViewCreated()");
    }

    protected abstract void initParams(Bundle bundle);

    protected abstract void doBusiness(Activity mActivity);

    protected abstract int getLayoutId();

    /**
     * findViewById
     *
     * @param id
     * @param <T>
     * @return
     */
    @SuppressWarnings("unchecked")
    protected <T extends View> T $(int id) {
        if (mRootView == null) {
            return null;
        }
        return (T) mRootView.findViewById(id);
    }

    @Override
    public void onStart() {
        super.onStart();
        LogUtil.d("onStart", TAG + "-->onStart()");
    }

    @Override
    public void onResume() {
        super.onResume();
        LogUtil.d("onResume", TAG + "-->onResume()");
    }

    @Override
    public void onStop() {
        super.onStop();
        LogUtil.d("onStop", TAG + "-->onStop()");
    }


    @Override
    public void onPause() {
        super.onPause();
        LogUtil.d("onPause", TAG + "-->onPause()");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        LogUtil.d("onDestroy", TAG + "-->onDestroy()");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        LogUtil.d("onDestroyView", TAG + "-->onDestroyView()");
        ButterKnife.bind(getActivity()).unbind();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        LogUtil.d("onDetach", TAG + "-->onDetach()");
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        // 如果还没有加载过数据 && 用户切换到了这个fragment
        // 那就开始加载数据
        if (!mHaveLoadData && isVisibleToUser) {
            loadDataStart();
            mHaveLoadData = true;
        }
    }

}
