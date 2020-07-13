package com.xiaolu.mylibrarytool.bean;

import com.flyco.tablayout.listener.CustomTabEntity;

public class TextTabEntity implements CustomTabEntity {
    public String title;

    public TextTabEntity(String title) {
        this.title = title;
    }

    @Override
    public String getTabTitle() {
        return title;
    }

    @Override
    public int getTabSelectedIcon() {
        return 0;
    }

    @Override
    public int getTabUnselectedIcon() {
        return 0;
    }

}
