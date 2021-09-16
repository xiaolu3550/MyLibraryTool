package com.xiaolu.mylibrarytool.bean;

public class BaseObjectBean<T> {
    /**
     * sign : null
     * merchantId : null
     * data : {}
     */
    private String code;
    private String msg;
    private T data;

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

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "BaseObjectBean{" +
                "data=" + data +
                '}';
    }
}
