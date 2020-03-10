package com.jundu.mylibrary.weigit;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ClipDrawable;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.util.AttributeSet;
import android.view.Gravity;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

public class MyWebView extends WebView {
    private ProgressBar progressbar;

    public MyWebView(Context context) {
        super(context);
    }

    public MyWebView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initProgressBar(context);
        openJavaScript();
        setWebViewClient(new WebViewClient());
        setWebChromeClient(new WebChromeClient());
    }

    public MyWebView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initProgressBar(context);
        openJavaScript();
        setWebViewClient(new WebViewClient());
        setWebChromeClient(new WebChromeClient());
    }


    private void init() {
        WebSettings mWebSettings = getSettings();
        //声明WebSettings子类
        //如果访问的页面中要与Javascript交互，则webview必须设置支持Javascript
        mWebSettings.setJavaScriptEnabled(true);
        // 若加载的 html 里有JS 在执行动画等操作，会造成资源浪费（CPU、电量）
        // 在 onStop 和 onResume 里分别把 setJavaScriptEnabled() 给设置成 false 和 true 即可
        //支持插件
        // mWebSettings.setPluginsEnabled(true);
        //设置自适应屏幕，两者合用
        mWebSettings.setDomStorageEnabled(true);//开启DOM
        mWebSettings.setUseWideViewPort(true); //将图片调整到适合webview的大小
        mWebSettings.setLoadWithOverviewMode(true); // 缩放至屏幕的大小
        //缩放操作
        mWebSettings.setSupportZoom(true); //支持缩放，默认为true。是下面那个的前提。
        mWebSettings.setBuiltInZoomControls(true); //设置内置的缩放控件。若为false，则该WebView不可缩放
        mWebSettings.setDisplayZoomControls(false); //隐藏原生的缩放控件
        //其他细节操作
        mWebSettings.setDefaultTextEncodingName("utf-8");//设置字符编码//设置web页面
        mWebSettings.setCacheMode(WebSettings.LOAD_NO_CACHE); //关闭webview中缓存
        mWebSettings.setAllowFileAccess(true); //设置可以访问文件
        mWebSettings.setJavaScriptCanOpenWindowsAutomatically(true); //支持通过JS打开新窗口
        mWebSettings.setLoadsImagesAutomatically(true); //支持自动加载图片
        mWebSettings.setDefaultTextEncodingName("utf-8");//设置编码格式
        mWebSettings.setDefaultFontSize(50);
        // mWebSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NORMAL);//支持内容从新布局
        mWebSettings.setAllowContentAccess(true); // 是否可访问Content Provider的资源，默认值 true
        mWebSettings.setAllowFileAccess(true);    // 是否可访问本地文件，默认值 true
        // 是否允许通过file url加载的Javascript读取本地文件，默认值 false
        mWebSettings.setAllowFileAccessFromFileURLs(false);
        // 是否允许通过file url加载的Javascript读取全部资源(包括文件,http,https)，默认值 false
        mWebSettings.setAllowUniversalAccessFromFileURLs(false);
        //mWebSettings.setTextSize(WebSettings.TextSize.LARGER);//设置网页文字大小
        //mWebSettings.setBlockNetworkImage(true);//提高网页加载速度，暂时阻塞图片加载，然后网页加载好了，在进行加载图片
        mWebSettings.setAppCacheEnabled(false);//开启缓存机制
        mWebSettings.setNeedInitialFocus(true); //当webview调用requestFocus时为webview设置节点
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            mWebSettings.setMixedContentMode(
                    mWebSettings.MIXED_CONTENT_COMPATIBILITY_MODE);
        }
    }

    private void initProgressBar(Context context) {
        progressbar = new ProgressBar(context, null, android.R.attr.progressBarStyleHorizontal);
        progressbar.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, dp2px(context, 3), 0, 0));
        //改变progressbar默认进度条的颜色（深红色）为Color.GREEN  
        progressbar.setProgressDrawable(new ClipDrawable(new ColorDrawable(Color.GREEN), Gravity.LEFT, ClipDrawable.HORIZONTAL));
        addView(progressbar);
    }

    /**
     * 方法描述：启用支持javascript
     */
    private void openJavaScript() {
        init();
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
    }

    /**
     * 方法描述：根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public int dp2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 类描述：显示WebView加载的进度情况
     */
    public class WebChromeClient extends android.webkit.WebChromeClient {
        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            if (newProgress == 100) {
                //view.getSettings().setBlockNetworkImage(false);
                progressbar.setVisibility(GONE);
            } else {
                if (progressbar.getVisibility() == GONE)
                    progressbar.setVisibility(VISIBLE);

                progressbar.setProgress(newProgress);
            }
            //super.onProgressChanged(view, newProgress);
        }


    }
}  