package com.xiaolu.mylibrarytool.bean;

import java.util.List;

public class BaseListBean<T> {
    /**
     * sign : null
     * merchantId : null
     * data : {}
     */
    private String code;
    private String msg;
    private List<T> data;

    public boolean isSuccess() {
        return code.equals("000000");
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "BaseListBean{" +
                "data=" + data +
                '}';
    }
}
