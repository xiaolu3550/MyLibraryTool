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
    private String sign;
    private String merchantId;

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
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


    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
    }

    @Override
    public String toString() {
        return "BaseObjectBean{" +
                "code='" + code + '\'' +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                ", sign='" + sign + '\'' +
                ", merchantId='" + merchantId + '\'' +
                '}';
    }
}
