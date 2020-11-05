package com.xiaolu.mylibrarykotlin.weigit

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import androidx.viewpager.widget.ViewPager

/**
 * 禁止左右滑动
 */
class NoScrollViewPager : ViewPager {
    private var isScroll = false

    constructor(context: Context) : super(context) {}
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {}

    /**
     * 1.dispatchTouchEvent一般情况不做处理
     * ,如果修改了默认的返回值,子孩子都无法收到事件
     */
    override fun dispatchTouchEvent(ev: MotionEvent): Boolean {
        return super.dispatchTouchEvent(ev)
    }

    /**
     * 是否拦截
     * 拦截:会走到自己的onTouchEvent方法里面来
     * 不拦截:事件传递给子孩子
     */
    override fun onInterceptTouchEvent(ev: MotionEvent): Boolean {
        return if (isScroll) {
            super.onInterceptTouchEvent(ev)
        } else {
            false
        }
    }

    /**
     * 是否消费事件
     * 消费:事件就结束
     * 不消费:往父控件传
     */
    override fun onTouchEvent(ev: MotionEvent): Boolean {
        return if (isScroll) {
            super.onTouchEvent(ev)
        } else {
            true // 可行,消费,拦截事件
        }
    }

    fun setScroll(scroll: Boolean) {
        isScroll = scroll
    }
}