package com.xiaolu.mylibrarykotlin.utils

import android.graphics.Color
import android.graphics.drawable.Drawable
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import com.xiaolu.mylibrarykotlin.R

/**
 * @author: zhaol
 * @createdOn: 2018/8/28 9:56
 * @description: $desc$
 */
class ToolbarHelper(private val toolbar: Toolbar) {

    fun setBackground(color: Int) {
        toolbar.setBackgroundColor(color)
    }

    /**
     * Toolbar 标题
     *
     * @param title 标题
     */
    fun setTitle(title: String?, color: Int, size: Int) {
        val titleTV = toolbar.findViewById<TextView>(R.id.toolbar_title)
        titleTV.setTextColor(color)
        titleTV.textSize = size.toFloat()
        titleTV.text = title
    }

    val title: TextView
        get() = toolbar.findViewById(R.id.toolbar_title)

    /**
     * 菜单栏设置
     *
     * @param menuTitle TextView标题
     * @param listener  点击事件
     * @param color     颜色
     * @param size      字体大小
     */
    fun setMenuTitle(menuTitle: String?, listener: View.OnClickListener?, color: String?, size: Float) {
        val menuTitleTV = toolbar.findViewById<TextView>(R.id.toolbar_menu_title)
        val menuTitleImage = toolbar.findViewById<ImageView>(R.id.toolbar_menu_title_image)
        menuTitleImage.visibility = View.GONE
        menuTitleTV.visibility = View.VISIBLE
        menuTitleTV.text = menuTitle
        menuTitleTV.textSize = size
        menuTitleTV.setOnClickListener(listener)
        menuTitleTV.setTextColor(Color.parseColor(color))
    }

    /**
     * 菜单栏图片设置
     *
     * @param listener 点击事件
     * @param resId    图片
     */
    fun setMenuTitleImage(resId: Int, listener: View.OnClickListener?) {
        val menuTitleImage = toolbar.findViewById<ImageView>(R.id.toolbar_menu_title_image)
        val menuTitleTV = toolbar.findViewById<TextView>(R.id.toolbar_menu_title)
        menuTitleImage.visibility = View.VISIBLE
        menuTitleTV.visibility = View.GONE
        menuTitleImage.setImageResource(resId)
        menuTitleImage.setOnClickListener(listener)
    }

    /**
     * 菜单栏图片设置二
     *
     * @param listener 点击事件
     * @param resId    图片
     */
    fun setMenuTitleImageTwo(resId: Int, listener: View.OnClickListener?) {
        val toolbar_menu_title_image_two = toolbar.findViewById<ImageView>(R.id.toolbar_menu_title_image_two)
        val menuTitleTV = toolbar.findViewById<TextView>(R.id.toolbar_menu_title)
        menuTitleTV.visibility = View.GONE
        toolbar_menu_title_image_two.visibility = View.VISIBLE
        toolbar_menu_title_image_two.setImageResource(resId)
        toolbar_menu_title_image_two.setOnClickListener(listener)
    }

    /**
     * 左侧标题设置
     *
     * @param listener 点击事件
     * @param text     图片
     */
    fun seLeftTitle(text: String?, listener: View.OnClickListener?) {
        val toolbar_title_left = toolbar.findViewById<TextView>(R.id.toolbar_title_left)
        toolbar_title_left.text = text
        toolbar_title_left.setOnClickListener(listener)
    }

    val textView: TextView
        get() = toolbar.findViewById(R.id.toolbar_menu_title)

    /**
     * 左侧点击事件
     *
     * @param listener 点击事件
     */
    fun setOnClick(listener: View.OnClickListener?) {
        toolbar.setNavigationOnClickListener(listener)
    }

    /**
     * 隐藏左侧返回键
     */
    fun visible() {
        //mToolbar.setNavigationIcon(R.color.white);
    }

    /**
     * 左侧返回键
     *
     * @param resId 图片
     */
    fun setPic(resId: Int) {
        toolbar.setNavigationIcon(resId)
    }

    /**
     * 左侧返回键
     *
     * @param drawable 图片
     */
    fun setPic(drawable: Drawable?) {
        toolbar.navigationIcon = drawable
    }

}