package com.xiaolu.mylibrary.utils;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;

import com.xiaolu.mylibrary.R;


/**
 * @author: zhaol
 * @createdOn: 2018/8/28 9:56
 * @description: $desc$
 */
public class ToolbarHelper {
    private Toolbar mToolbar;

    public ToolbarHelper(Toolbar toolbar) {
        this.mToolbar = toolbar;

    }

    public Toolbar getToolbar() {
        return mToolbar;
    }

    public void setBackground(int color) {
        mToolbar.setBackgroundColor(color);
    }

    /**
     * Toolbar 标题
     *
     * @param title 标题
     */
    public void setTitle(String title, int color, int size) {
        TextView titleTV = mToolbar.findViewById(R.id.toolbar_title);
        titleTV.setTextColor(color);
        titleTV.setTextSize(size);
        titleTV.setText(title);
    }

    public TextView getTitle() {
        return mToolbar.findViewById(R.id.toolbar_title);
    }

    /**
     * 菜单栏设置
     *
     * @param menuTitle TextView标题
     * @param listener  点击事件
     * @param color     颜色
     * @param size      字体大小
     */
    public void setMenuTitle(String menuTitle, View.OnClickListener listener, String color, float size) {
        TextView menuTitleTV = mToolbar.findViewById(R.id.toolbar_menu_title);
        ImageView menuTitleImage = mToolbar.findViewById(R.id.toolbar_menu_title_image);
        menuTitleImage.setVisibility(View.GONE);
        menuTitleTV.setVisibility(View.VISIBLE);
        menuTitleTV.setText(menuTitle);
        menuTitleTV.setTextSize(size);
        menuTitleTV.setOnClickListener(listener);
        menuTitleTV.setTextColor(Color.parseColor(color));
    }

    /**
     * 菜单栏图片设置
     *
     * @param listener 点击事件
     * @param resId    图片
     */
    public void setMenuTitleImage(int resId, View.OnClickListener listener) {
        ImageView menuTitleImage = mToolbar.findViewById(R.id.toolbar_menu_title_image);
        TextView menuTitleTV = mToolbar.findViewById(R.id.toolbar_menu_title);
        menuTitleImage.setVisibility(View.VISIBLE);
        menuTitleTV.setVisibility(View.GONE);
        menuTitleImage.setImageResource(resId);
        menuTitleImage.setOnClickListener(listener);
    }

    /**
     * 菜单栏图片设置二
     *
     * @param listener 点击事件
     * @param resId    图片
     */
    public void setMenuTitleImageTwo(int resId, View.OnClickListener listener) {
        ImageView toolbar_menu_title_image_two = mToolbar.findViewById(R.id.toolbar_menu_title_image_two);
        TextView menuTitleTV = mToolbar.findViewById(R.id.toolbar_menu_title);
        menuTitleTV.setVisibility(View.GONE);
        toolbar_menu_title_image_two.setVisibility(View.VISIBLE);
        toolbar_menu_title_image_two.setImageResource(resId);
        toolbar_menu_title_image_two.setOnClickListener(listener);
    }

    /**
     * 左侧标题设置
     *
     * @param listener 点击事件
     * @param text     图片
     */
    public void seLeftTitle(String text, View.OnClickListener listener) {
        TextView toolbar_title_left = mToolbar.findViewById(R.id.toolbar_title_left);
        toolbar_title_left.setText(text);
        toolbar_title_left.setOnClickListener(listener);
    }

    public TextView getTextView() {
        TextView menuTitleTV = mToolbar.findViewById(R.id.toolbar_menu_title);
        return menuTitleTV;
    }

    /**
     * 左侧点击事件
     *
     * @param listener 点击事件
     */
    public void setOnClick(View.OnClickListener listener) {
        mToolbar.setNavigationOnClickListener(listener);
    }

    /**
     * 隐藏左侧返回键
     */
    public void visible() {
        //mToolbar.setNavigationIcon(R.color.white);
    }

    /**
     * 左侧返回键
     *
     * @param resId 图片
     */
    public void setPic(int resId) {
        mToolbar.setNavigationIcon(resId);
    }

    /**
     * 左侧返回键
     *
     * @param drawable 图片
     */
    public void setPic(Drawable drawable) {
        mToolbar.setNavigationIcon(drawable);
    }

}
