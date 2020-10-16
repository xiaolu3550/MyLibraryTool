package com.xiaolu.mylibrary.dialog;

import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.tamsiree.rxkit.RxImageTool;


public class CustomDialog extends DialogFragment {
    private Context context;
    private int height, width;
    private boolean cancelTouchout;
    private View view;
    private int resStyle = -1;
    private int gravity;

    @Override
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        setStyle(DialogFragment.STYLE_NORMAL, resStyle);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getDialog().setCanceledOnTouchOutside(cancelTouchout);
        Window win = getDialog().getWindow();
        WindowManager.LayoutParams lp = win.getAttributes();
        lp.gravity = gravity;
        lp.height = height;
        lp.width = width;
        win.setAttributes(lp);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    private CustomDialog(Builder builder) {
        context = builder.context;
        height = builder.height;
        width = builder.width;
        cancelTouchout = builder.cancelTouchout;
        view = builder.view;
        resStyle = builder.resStyle;
        gravity = builder.gravity;
    }

    public static final class Builder {

        private Context context;
        private int height, width;
        private boolean cancelTouchout;
        private View view;
        private int resStyle = -1;
        private int gravity = Gravity.CENTER;


        public Builder(Context context,int resView) {
            this.context = context;
            view = LayoutInflater.from(context).inflate(resView, null);
        }

        /*public Builder view(int resView) {
            view = LayoutInflater.from(context).inflate(resView, null);
            return this;
        }*/

        public Builder heightPx(int val) {
            height = val;
            return this;
        }

        public Builder widthPx(int val) {
            width = val;
            return this;
        }

        public Builder heightDp(int val) {
            height = RxImageTool.dip2px(val);
            return this;
        }

        public Builder widthDp(int val) {
            width = RxImageTool.dip2px(val);
            return this;
        }

        public Builder heightDimenRes(int dimenRes) {
            height = context.getResources().getDimensionPixelOffset(dimenRes);
            return this;
        }

        public Builder widthDimenRes(int dimenRes) {
            width = context.getResources().getDimensionPixelOffset(dimenRes);
            return this;
        }

        public Builder style(int resStyle) {
            this.resStyle = resStyle;
            return this;
        }

        public Builder gravity(int gravity) {
            this.gravity = gravity;
            return this;
        }

        public Builder cancelTouchOut(boolean val) {
            cancelTouchout = val;
            return this;
        }

        public Builder addViewOnclick(int viewRes, View.OnClickListener listener) {
            view.findViewById(viewRes).setOnClickListener(listener);
            return this;
        }

        public <T extends View> T getView(int viewRes) {
            return view.findViewById(viewRes);
        }

        public CustomDialog build() {
            return new CustomDialog(this);
        }
    }
}
