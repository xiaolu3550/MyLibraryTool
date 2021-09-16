package com.xiaolu.mylibrarytool.bean;


import com.xiaolu.mylibrary.utils.GsonUtils;

public class LoginRequestBean {

    /**
     * account :
     * area :
     * registerCode :
     * verificationCode :
     */

    private String account;
    private String area;
    private String registerCode;
    private String verificationCode;

    public LoginRequestBean(String account, String area, String registerCode, String verificationCode) {
        this.account = account;
        this.area = area;
        this.registerCode = registerCode;
        this.verificationCode = verificationCode;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getRegisterCode() {
        return registerCode;
    }

    public void setRegisterCode(String registerCode) {
        this.registerCode = registerCode;
    }

    public String getVerificationCode() {
        return verificationCode;
    }

    public void setVerificationCode(String verificationCode) {
        this.verificationCode = verificationCode;
    }

    @Override
    public String toString() {
        return GsonUtils.toJson(this);
    }
}
