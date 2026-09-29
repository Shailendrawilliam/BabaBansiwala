package com.bababansiwalanew.Login.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by Lalit on 08-04-2017.
 */

public class LoginData {

    private String OTP;
    private String SessionID;
    private String UName;
    private String UMail;
    private String UMobile;
    @SerializedName("DESCRIPTION")
    @Expose
    private String dESCRIPTION;

    public String getdESCRIPTION() {
        return dESCRIPTION;
    }

    public void setdESCRIPTION(String dESCRIPTION) {
        this.dESCRIPTION = dESCRIPTION;
    }

    private String UserID;
    private String IsExist;
    private String PinPassword;
    private String PrepaidWallet;
    private String UtilityWallet;
    private String Key;
    private String RoleId;
    private String supportEmail;
    private String supportNumber;
    private String icon;
    private String OutletId;
    private String dueamount;
    private String duedate;

    private String billnumber;
    private String billdate;
    private String billperiod;
    private String reference_id;
    private String MonthlyRecharge;
    private double Balance;
    private String customerName;
    private String status;
    private String NextRechargeDate;
    private String lastrechargedate;
    private String lastrechargeamount;
    private String planname;
    private String rootNode_Id;
    private String Rmn;
    @SerializedName(value = "STATUS")
    @Expose
    private String STATUS;

    public String getSTATUS() {
        return STATUS;
    }

    public void setSTATUS(String STATUS) {
        this.STATUS = STATUS;
    }

    public String getUserID() {
        return UserID;
    }

    public void setUserID(String userID) {
        UserID = userID;
    }


    public String getRmn() {
        return Rmn;
    }

    public void setRmn(String rmn) {
        Rmn = rmn;
    }

    public String getMonthlyRecharge() {
        return MonthlyRecharge;
    }

    public void setMonthlyRecharge(String monthlyRecharge) {
        MonthlyRecharge = monthlyRecharge;
    }

    public double getBalance() {
        return Balance;
    }

    public void setBalance(double balance) {
        Balance = balance;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getNextRechargeDate() {
        return NextRechargeDate;
    }

    public void setNextRechargeDate(String nextRechargeDate) {
        NextRechargeDate = nextRechargeDate;
    }

    public String getLastrechargedate() {
        return lastrechargedate;
    }

    public void setLastrechargedate(String lastrechargedate) {
        this.lastrechargedate = lastrechargedate;
    }

    public String getLastrechargeamount() {
        return lastrechargeamount;
    }

    public void setLastrechargeamount(String lastrechargeamount) {
        this.lastrechargeamount = lastrechargeamount;
    }

    public String getPlanname() {
        return planname;
    }

    public void setPlanname(String planname) {
        this.planname = planname;
    }

    public String getRootNode_Id() {
        return rootNode_Id;
    }

    public void setRootNode_Id(String rootNode_Id) {
        this.rootNode_Id = rootNode_Id;
    }

    private ArrayList<broadbandData> billdetails;
    private ArrayList<broadbandData> customerparamsdetails;
    private ArrayList<broadbandData> additionaldetails;
    private String BusinessModuleID;
    private String DeviceStatus;
    private String Id;
    private String ImageUrl;
    private String Entrydate;

    public String getBusinessModuleID() {
        return BusinessModuleID;
    }

    public void setBusinessModuleID(String businessModuleID) {
        BusinessModuleID = businessModuleID;
    }

    public String getDeviceStatus() {
        return DeviceStatus;
    }

    public void setDeviceStatus(String deviceStatus) {
        DeviceStatus = deviceStatus;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getImageUrl() {
        return ImageUrl;
    }

    public void setImageUrl(String imageUrl) {
        ImageUrl = imageUrl;
    }

    public String getEntrydate() {
        return Entrydate;
    }

    public void setEntrydate(String entrydate) {
        Entrydate = entrydate;
    }

    public String getDueamount() {
        return dueamount;
    }

    public void setDueamount(String dueamount) {
        this.dueamount = dueamount;
    }

    public String getDuedate() {
        return duedate;
    }

    public void setDuedate(String duedate) {
        this.duedate = duedate;
    }

    public String getCustomername() {
        return customername;
    }

    public void setCustomername(String customername) {
        this.customername = customername;
    }

    public String getBillnumber() {
        return billnumber;
    }

    public void setBillnumber(String billnumber) {
        this.billnumber = billnumber;
    }

    public String getBilldate() {
        return billdate;
    }

    public void setBilldate(String billdate) {
        this.billdate = billdate;
    }

    public String getBillperiod() {
        return billperiod;
    }

    public void setBillperiod(String billperiod) {
        this.billperiod = billperiod;
    }

    public String getReference_id() {
        return reference_id;
    }

    public void setReference_id(String reference_id) {
        this.reference_id = reference_id;
    }

    public ArrayList<broadbandData> getBilldetails() {
        return billdetails;
    }

    public void setBilldetails(ArrayList<broadbandData> billdetails) {
        this.billdetails = billdetails;
    }

    public ArrayList<broadbandData> getCustomerparamsdetails() {
        return customerparamsdetails;
    }

    public void setCustomerparamsdetails(ArrayList<broadbandData> customerparamsdetails) {
        this.customerparamsdetails = customerparamsdetails;
    }

    public ArrayList<broadbandData> getAdditionaldetails() {
        return additionaldetails;
    }

    public void setAdditionaldetails(ArrayList<broadbandData> additionaldetails) {
        this.additionaldetails = additionaldetails;
    }

    public String getOutletId() {
        return OutletId;
    }

    public void setOutletId(String outletId) {
        OutletId = outletId;
    }

    public String getSupportEmail() {
        return supportEmail;
    }

    public void setSupportEmail(String supportEmail) {
        this.supportEmail = supportEmail;
    }

    public String getSupportNumber() {
        return supportNumber;
    }

    public void setSupportNumber(String supportNumber) {
        this.supportNumber = supportNumber;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getRoleId() {
        return RoleId;
    }

    public void setRoleId(String roleId) {
        RoleId = roleId;
    }

    public String getOTP() {
        return OTP;
    }

    public void setOTP(String OTP) {
        this.OTP = OTP;
    }

    public String getUName() {
        return UName;
    }

    public void setUName(String UName) {
        this.UName = UName;
    }

    public String getSessionID() {
        return SessionID;
    }

    public void setSessionID(String sessionID) {
        SessionID = sessionID;
    }

    public String getUMail() {
        return UMail;
    }

    public void setUMail(String UMail) {
        this.UMail = UMail;
    }

    public String getUMobile() {
        return UMobile;
    }

    public void setUMobile(String UMobile) {
        this.UMobile = UMobile;
    }

    public String getIsExist() {
        return IsExist;
    }

    public void setIsExist(String isExist) {
        IsExist = isExist;
    }

    public String getPinPassword() {
        return PinPassword;
    }

    public void setPinPassword(String pinPassword) {
        PinPassword = pinPassword;
    }

    public String getPrepaidWallet() {
        return PrepaidWallet;
    }

    public void setPrepaidWallet(String prepaidWallet) {
        PrepaidWallet = prepaidWallet;
    }

    public String getKey() {
        return Key;
    }

    public void setKey(String key) {
        Key = key;
    }

    public String getUtilityWallet() {
        return UtilityWallet;
    }

    public void setUtilityWallet(String utilityWallet) {
        UtilityWallet = utilityWallet;
    }
//    @SerializedName("MonthlyRecharge")
//    @Expose
    private String monthlyRecharge;
//    @SerializedName("Balance")
//    @Expose
    private String balance;

//    @SerializedName("Rmn")
//    @Expose
    private String rmn;
//
//    @SerializedName("rootNode_Id")
//    @Expose
    private String rootNodeId;

//
//    @SerializedName("customername")
//    @Expose
    private String customername;


    public void setBalance(String balance) {
        this.balance = balance;
    }







    public String getRootNodeId() {
        return rootNodeId;
    }

    public void setRootNodeId(String rootNodeId) {
        this.rootNodeId = rootNodeId;
    }

}