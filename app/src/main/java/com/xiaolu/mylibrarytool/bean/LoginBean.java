package com.xiaolu.mylibrarytool.bean;

public class LoginBean {

    /**
     * id : 11
     * uid :
     * token : 3faaaf2c1cd8439d9ea5a79ff23cbb66
     * status : 1
     * phone : 15636808536
     * address : 15wmugcCqxvT3jm1GMURzVcWdvbkgFzu2a
     * userId : 09673825
     * headUrl :
     * nickName : 昵称09673825
     * biAccount :
     * aliAccount :
     * wxAccount :
     * inviteCode : YizOZb
     * registerCode :
     * payPassword :
     * createTime : 1629266161591
     * updateTime : 1629943920744
     * area : +86
     * lastLogin : 1629943920728
     * birthday : null
     * sex : 1
     */

    private int id;
    private String uid;
    private String token;
    private int status;
    private String phone;
    private String address;
    private String userId;
    private String headUrl;
    private String nickName;
    private String biAccount;
    private String aliAccount;
    private String wxAccount;
    private String inviteCode;
    private String registerCode;
    private String payPassword;
    private long createTime;
    private long updateTime;
    private String area;
    private long lastLogin;
    private String birthday;
    private int sex;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getHeadUrl() {
        return headUrl;
    }

    public void setHeadUrl(String headUrl) {
        this.headUrl = headUrl;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getBiAccount() {
        return biAccount;
    }

    public void setBiAccount(String biAccount) {
        this.biAccount = biAccount;
    }

    public String getAliAccount() {
        return aliAccount;
    }

    public void setAliAccount(String aliAccount) {
        this.aliAccount = aliAccount;
    }

    public String getWxAccount() {
        return wxAccount;
    }

    public void setWxAccount(String wxAccount) {
        this.wxAccount = wxAccount;
    }

    public String getInviteCode() {
        return inviteCode;
    }

    public void setInviteCode(String inviteCode) {
        this.inviteCode = inviteCode;
    }

    public String getRegisterCode() {
        return registerCode;
    }

    public void setRegisterCode(String registerCode) {
        this.registerCode = registerCode;
    }

    public String getPayPassword() {
        return payPassword;
    }

    public void setPayPassword(String payPassword) {
        this.payPassword = payPassword;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(long updateTime) {
        this.updateTime = updateTime;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public long getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(long lastLogin) {
        this.lastLogin = lastLogin;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    @Override
    public String toString() {
        return "LoginBean{" +
                "id=" + id +
                ", uid='" + uid + '\'' +
                ", token='" + token + '\'' +
                ", status=" + status +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                ", userId='" + userId + '\'' +
                ", headUrl='" + headUrl + '\'' +
                ", nickName='" + nickName + '\'' +
                ", biAccount='" + biAccount + '\'' +
                ", aliAccount='" + aliAccount + '\'' +
                ", wxAccount='" + wxAccount + '\'' +
                ", inviteCode='" + inviteCode + '\'' +
                ", registerCode='" + registerCode + '\'' +
                ", payPassword='" + payPassword + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", area='" + area + '\'' +
                ", lastLogin=" + lastLogin +
                ", birthday='" + birthday + '\'' +
                ", sex=" + sex +
                '}';
    }
}
