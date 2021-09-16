package com.xiaolu.mylibrarytool.bean;

public class BannerBean {

    /**
     * id : 1433285899872923650
     * operationBannerId : e0846a100c7d4c7a9df91e704a79761e
     * bannerName : 轮播图
     * bannerImage : https://xls-data-test.oss-cn-shanghai.aliyuncs.com/manage/img/20210902/20210902122904663_464770.png
     * bannerType : true
     * url :
     * number : https://ai.taobao.com
     * displayOrder : 1
     * status : false
     * merchantId : e3ac4b4a4e65460dbd9f1ec6cbd7a900
     * createTime : 1630556962351
     * updateTime : 1630556962351
     */

    private long id;
    private String operationBannerId;
    private String bannerName;
    private String bannerImage;
    private boolean bannerType;
    private String url;
    private String number;
    private int displayOrder;
    private boolean status;
    private String merchantId;
    private long createTime;
    private long updateTime;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getOperationBannerId() {
        return operationBannerId;
    }

    public void setOperationBannerId(String operationBannerId) {
        this.operationBannerId = operationBannerId;
    }

    public String getBannerName() {
        return bannerName;
    }

    public void setBannerName(String bannerName) {
        this.bannerName = bannerName;
    }

    public String getBannerImage() {
        return bannerImage;
    }

    public void setBannerImage(String bannerImage) {
        this.bannerImage = bannerImage;
    }

    public boolean isBannerType() {
        return bannerType;
    }

    public void setBannerType(boolean bannerType) {
        this.bannerType = bannerType;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public int getDisplayOrder() {
        return displayOrder;
    }

    public void setDisplayOrder(int displayOrder) {
        this.displayOrder = displayOrder;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
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

    @Override
    public String toString() {
        return "BannerBean{" +
                "id=" + id +
                ", operationBannerId='" + operationBannerId + '\'' +
                ", bannerName='" + bannerName + '\'' +
                ", bannerImage='" + bannerImage + '\'' +
                ", bannerType=" + bannerType +
                ", url='" + url + '\'' +
                ", number='" + number + '\'' +
                ", displayOrder=" + displayOrder +
                ", status=" + status +
                ", merchantId='" + merchantId + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}
