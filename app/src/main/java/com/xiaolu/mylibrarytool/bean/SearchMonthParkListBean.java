package com.xiaolu.mylibrarytool.bean;

import java.io.Serializable;
import java.util.List;

public class SearchMonthParkListBean implements Serializable {
    /**
     * address : 北京市海淀区摩天大厦综合办公楼
     * city : 北京市市辖区
     * company : 君度科技
     * createTime : 2019-12-10 16:44:02
     * createUserId : 1
     * del : 0
     * district : 东城区
     * id : 10
     * latitude : 118.820
     * longitude : 31.890
     * manageType : 自营
     * monthCard : 1
     * name : 北京停车场
     * nearByDistances : 12708.33
     * onlinePay : 0
     * parkChargRules : [{"accountType":1,"createTime":"2019-12-25 13:51:29","createUserId":1,"del":0,"freeTime":"03:00:00 - 05:05:00","freeduration":30,"id":10,"increaseFee":4,"increaseTime":6,"parkId":"10","ruleName":"通用","startDuration":5,"startIncreaseAmount":3,"status":"1","topfee":2,"updateTime":"2020-01-15 17:54:00","updateUserId":1,"vehicleType":3}]
     * parkCount : 100
     * parkCoupon : 0
     * parkLess : 50
     * parkMonthsCardPackages : [{"cost":10000,"discount":0,"monthNo":1,"originalCost":10000,"packageId":"1","status":1,"type":1,"updateTime":"2020-03-10 17:18:25"},{"cost":25000,"discount":5000,"monthNo":3,"originalCost":30000,"packageId":"2","status":1,"type":2},{"cost":95000,"discount":25000,"monthNo":12,"originalCost":120000,"packageId":"3","status":1,"type":3}]
     * parkType : 1
     * photo : /profile/avatar/3836c966c3f26938cd50e23e401e735b.jpg
     * province : 北京市
     * status : 0
     * updateTime : 2020-01-16 11:03:02
     * updateUserId : 1
     */

    private String address;
    private String city;
    private String company;
    private String createTime;
    private int createUserId;
    private int del;
    private String district;
    private String id;
    private String latitude;
    private String longitude;
    private String manageType;
    private int monthCard;
    private String name;
    private double nearByDistances;
    private int onlinePay;
    private int parkCount;
    private int parkCoupon;
    private int parkLess;
    private int parkType;
    private String photo;
    private String province;
    private int status;
    private String updateTime;
    private int updateUserId;
    private List<ParkChargRulesBean> parkChargRules;
    private List<ParkMonthsCardPackagesBean> parkMonthsCardPackages;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public int getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(int createUserId) {
        this.createUserId = createUserId;
    }

    public int getDel() {
        return del;
    }

    public void setDel(int del) {
        this.del = del;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getManageType() {
        return manageType;
    }

    public void setManageType(String manageType) {
        this.manageType = manageType;
    }

    public int getMonthCard() {
        return monthCard;
    }

    public void setMonthCard(int monthCard) {
        this.monthCard = monthCard;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getNearByDistances() {
        return nearByDistances;
    }

    public void setNearByDistances(double nearByDistances) {
        this.nearByDistances = nearByDistances;
    }

    public int getOnlinePay() {
        return onlinePay;
    }

    public void setOnlinePay(int onlinePay) {
        this.onlinePay = onlinePay;
    }

    public int getParkCount() {
        return parkCount;
    }

    public void setParkCount(int parkCount) {
        this.parkCount = parkCount;
    }

    public int getParkCoupon() {
        return parkCoupon;
    }

    public void setParkCoupon(int parkCoupon) {
        this.parkCoupon = parkCoupon;
    }

    public int getParkLess() {
        return parkLess;
    }

    public void setParkLess(int parkLess) {
        this.parkLess = parkLess;
    }

    public int getParkType() {
        return parkType;
    }

    public void setParkType(int parkType) {
        this.parkType = parkType;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public int getUpdateUserId() {
        return updateUserId;
    }

    public void setUpdateUserId(int updateUserId) {
        this.updateUserId = updateUserId;
    }

    public List<ParkChargRulesBean> getParkChargRules() {
        return parkChargRules;
    }

    public void setParkChargRules(List<ParkChargRulesBean> parkChargRules) {
        this.parkChargRules = parkChargRules;
    }

    public List<ParkMonthsCardPackagesBean> getParkMonthsCardPackages() {
        return parkMonthsCardPackages;
    }

    public void setParkMonthsCardPackages(List<ParkMonthsCardPackagesBean> parkMonthsCardPackages) {
        this.parkMonthsCardPackages = parkMonthsCardPackages;
    }

    public static class ParkChargRulesBean implements Serializable {
        /**
         * accountType : 1
         * createTime : 2019-12-25 13:51:29
         * createUserId : 1
         * del : 0
         * freeTime : 03:00:00 - 05:05:00
         * freeduration : 30
         * id : 10
         * increaseFee : 4
         * increaseTime : 6
         * parkId : 10
         * ruleName : 通用
         * startDuration : 5
         * startIncreaseAmount : 3
         * status : 1
         * topfee : 2
         * updateTime : 2020-01-15 17:54:00
         * updateUserId : 1
         * vehicleType : 3
         */

        private int accountType;
        private String createTime;
        private int createUserId;
        private int del;
        private String freeTime;
        private int freeduration;
        private int id;
        private float increaseFee;
        private int increaseTime;
        private String parkId;
        private String ruleName;
        private int startDuration;
        private float startIncreaseAmount;
        private String status;
        private float topfee;
        private String updateTime;
        private int updateUserId;
        private int vehicleType;

        public int getAccountType() {
            return accountType;
        }

        public void setAccountType(int accountType) {
            this.accountType = accountType;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public int getCreateUserId() {
            return createUserId;
        }

        public void setCreateUserId(int createUserId) {
            this.createUserId = createUserId;
        }

        public int getDel() {
            return del;
        }

        public void setDel(int del) {
            this.del = del;
        }

        public String getFreeTime() {
            return freeTime;
        }

        public void setFreeTime(String freeTime) {
            this.freeTime = freeTime;
        }

        public int getFreeduration() {
            return freeduration;
        }

        public void setFreeduration(int freeduration) {
            this.freeduration = freeduration;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public float getIncreaseFee() {
            return increaseFee;
        }

        public void setIncreaseFee(float increaseFee) {
            this.increaseFee = increaseFee;
        }

        public int getIncreaseTime() {
            return increaseTime;
        }

        public void setIncreaseTime(int increaseTime) {
            this.increaseTime = increaseTime;
        }

        public String getParkId() {
            return parkId;
        }

        public void setParkId(String parkId) {
            this.parkId = parkId;
        }

        public String getRuleName() {
            return ruleName;
        }

        public void setRuleName(String ruleName) {
            this.ruleName = ruleName;
        }

        public int getStartDuration() {
            return startDuration;
        }

        public void setStartDuration(int startDuration) {
            this.startDuration = startDuration;
        }

        public float getStartIncreaseAmount() {
            return startIncreaseAmount;
        }

        public void setStartIncreaseAmount(float startIncreaseAmount) {
            this.startIncreaseAmount = startIncreaseAmount;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public float getTopfee() {
            return topfee;
        }

        public void setTopfee(float topfee) {
            this.topfee = topfee;
        }

        public String getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(String updateTime) {
            this.updateTime = updateTime;
        }

        public int getUpdateUserId() {
            return updateUserId;
        }

        public void setUpdateUserId(int updateUserId) {
            this.updateUserId = updateUserId;
        }

        public int getVehicleType() {
            return vehicleType;
        }

        public void setVehicleType(int vehicleType) {
            this.vehicleType = vehicleType;
        }
    }

    public static class ParkMonthsCardPackagesBean implements Serializable {
        /**
         * cost : 10000
         * discount : 0
         * monthNo : 1
         * originalCost : 10000
         * packageId : 1
         * status : 1
         * type : 1
         * updateTime : 2020-03-10 17:18:25
         */

        private int cost;
        private int discount;
        private int monthNo;
        private int originalCost;
        private String packageId;
        private int status;
        private String type;
        private String updateTime;

        public int getCost() {
            return cost;
        }

        public void setCost(int cost) {
            this.cost = cost;
        }

        public int getDiscount() {
            return discount;
        }

        public void setDiscount(int discount) {
            this.discount = discount;
        }

        public int getMonthNo() {
            return monthNo;
        }

        public void setMonthNo(int monthNo) {
            this.monthNo = monthNo;
        }

        public int getOriginalCost() {
            return originalCost;
        }

        public void setOriginalCost(int originalCost) {
            this.originalCost = originalCost;
        }

        public String getPackageId() {
            return packageId;
        }

        public void setPackageId(String packageId) {
            this.packageId = packageId;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(String updateTime) {
            this.updateTime = updateTime;
        }
    }

}
