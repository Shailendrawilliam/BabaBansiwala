package com.bababansiwalanew.Login.dto;

/**
 * Created by Lalit on 08-04-2017.
 */

public class SaleReportObject {

    private String Todays;
    private String Months;
    private String LastMonths;
    private String UserName;
    private String Mobile;
    private String RemaingSalesTarget;
    private String SalesTarget;
    private String MobileNo;
    private String Name;
    private String Amont;
    private String SaleDate;

    public String getMobileNo() {
        return MobileNo;
    }

    public void setMobileNo(String mobileNo) {
        MobileNo = mobileNo;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getAmont() {
        return Amont;
    }

    public void setAmont(String amont) {
        Amont = amont;
    }

    public String getSaleDate() {
        return SaleDate;
    }

    public void setSaleDate(String saleDate) {
        SaleDate = saleDate;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getMobile() {
        return Mobile;
    }

    public void setMobile(String mobile) {
        Mobile = mobile;
    }

    public String getRemaingSalesTarget() {
        return RemaingSalesTarget;
    }

    public void setRemaingSalesTarget(String remaingSalesTarget) {
        RemaingSalesTarget = remaingSalesTarget;
    }

    public String getSalesTarget() {
        return SalesTarget;
    }

    public void setSalesTarget(String salesTarget) {
        SalesTarget = salesTarget;
    }

    public String getTodays() {
        return Todays;
    }

    public void setTodays(String todays) {
        Todays = todays;
    }

    public String getMonths() {
        return Months;
    }

    public void setMonths(String months) {
        Months = months;
    }

    public String getLastMonths() {
        return LastMonths;
    }

    public void setLastMonths(String lastMonths) {
        LastMonths = lastMonths;
    }
}
