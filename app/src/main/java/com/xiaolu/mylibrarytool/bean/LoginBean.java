package com.xiaolu.mylibrarytool.bean;

import android.os.Parcel;
import android.os.Parcelable;

public class LoginBean implements Parcelable {

    /**
     * searchValue : null
     * createBy : null
     * createTime : 2020-02-26 14:08:58
     * updateBy : null
     * updateTime : null
     * remark : null
     * params : nullƒ
     * ids : null
     * del : null
     * userId : 6887f05bbfa34b0cad4dc2c1b69d648f
     * userName : 默认用户
     * loginName : 15636808536
     * password :
     * phoneNumber : 15636808536
     * avatar : null
     * email : null
     * status : 0
     * delFlag : 0
     * balance : 0
     * token : ba2e83de9db1446991f6d429c0f0977d
     */

    private Object searchValue;
    private Object createBy;
    private String createTime;
    private Object updateBy;
    private Object updateTime;
    private Object remark;
    private Object params;
    private Object ids;
    private Object del;
    private String userId;
    private String userName;
    private String loginName;
    private String password;
    private String phoneNumber;
    private Object avatar;
    private Object email;
    private int status;
    private int delFlag;
    private int balance;
    private String token;

    public LoginBean() {
    }

    public LoginBean(Parcel in) {
        createTime = in.readString();
        userId = in.readString();
        userName = in.readString();
        loginName = in.readString();
        password = in.readString();
        phoneNumber = in.readString();
        status = in.readInt();
        delFlag = in.readInt();
        balance = in.readInt();
        token = in.readString();
    }

    public static final Creator<LoginBean> CREATOR = new Creator<LoginBean>() {
        @Override
        public LoginBean createFromParcel(Parcel in) {
            return new LoginBean(in);
        }

        @Override
        public LoginBean[] newArray(int size) {
            return new LoginBean[size];
        }
    };

    public Object getSearchValue() {
        return searchValue;
    }

    public void setSearchValue(Object searchValue) {
        this.searchValue = searchValue;
    }

    public Object getCreateBy() {
        return createBy;
    }

    public void setCreateBy(Object createBy) {
        this.createBy = createBy;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public Object getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(Object updateBy) {
        this.updateBy = updateBy;
    }

    public Object getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Object updateTime) {
        this.updateTime = updateTime;
    }

    public Object getRemark() {
        return remark;
    }

    public void setRemark(Object remark) {
        this.remark = remark;
    }

    public Object getParams() {
        return params;
    }

    public void setParams(Object params) {
        this.params = params;
    }

    public Object getIds() {
        return ids;
    }

    public void setIds(Object ids) {
        this.ids = ids;
    }

    public Object getDel() {
        return del;
    }

    public void setDel(Object del) {
        this.del = del;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Object getAvatar() {
        return avatar;
    }

    public void setAvatar(Object avatar) {
        this.avatar = avatar;
    }

    public Object getEmail() {
        return email;
    }

    public void setEmail(Object email) {
        this.email = email;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(int delFlag) {
        this.delFlag = delFlag;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "LoginBean{" +
                "searchValue=" + searchValue +
                ", createBy=" + createBy +
                ", createTime='" + createTime + '\'' +
                ", updateBy=" + updateBy +
                ", updateTime=" + updateTime +
                ", remark=" + remark +
                ", params=" + params +
                ", ids=" + ids +
                ", del=" + del +
                ", userId='" + userId + '\'' +
                ", userName='" + userName + '\'' +
                ", loginName='" + loginName + '\'' +
                ", password='" + password + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", avatar=" + avatar +
                ", email=" + email +
                ", status=" + status +
                ", delFlag=" + delFlag +
                ", balance=" + balance +
                ", token='" + token + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(createTime);
        dest.writeString(userId);
        dest.writeString(userName);
        dest.writeString(loginName);
        dest.writeString(password);
        dest.writeString(phoneNumber);
        dest.writeInt(status);
        dest.writeInt(delFlag);
        dest.writeInt(balance);
        dest.writeString(token);
    }
}
