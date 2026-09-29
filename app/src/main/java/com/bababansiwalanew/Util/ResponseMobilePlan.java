package com.bababansiwalanew.Util;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class ResponseMobilePlan {
    @SerializedName("Data")
    @Expose
    private DataM data;
    @SerializedName("Statuscode")
    @Expose
    private Integer statuscode;
    @SerializedName("Msg")
    @Expose
    private Object msg;
    @SerializedName("IsVersionValid")
    @Expose
    private Boolean isVersionValid;
    @SerializedName("IsAppValid")
    @Expose
    private Boolean isAppValid;
    @SerializedName("CheckID")
    @Expose
    private Integer checkID;
    @SerializedName("IsPasswordExpired")
    @Expose
    private Boolean isPasswordExpired;
    @SerializedName("MobileNo")
    @Expose
    private Object mobileNo;
    @SerializedName("EmailID")
    @Expose
    private Object emailID;
    @SerializedName("IsLookUpFromAPI")
    @Expose
    private Boolean isLookUpFromAPI;
    @SerializedName("IsDTHInfoCall")
    @Expose
    private Boolean isDTHInfoCall;
    @SerializedName("IsShowPDFPlan")
    @Expose
    private Boolean isShowPDFPlan;
    @SerializedName("SID")
    @Expose
    private Object sID;
    @SerializedName("IsOTPRequired")
    @Expose
    private Boolean isOTPRequired;
    @SerializedName("GetID")
    @Expose
    private Integer getID;
    @SerializedName("IsDTHInfo")
    @Expose
    private Boolean isDTHInfo;
    @SerializedName("IsRoffer")
    @Expose
    private Boolean isRoffer;

    public DataM getData() {
        return data;
    }

    public void setData(DataM data) {
        this.data = data;
    }

    public Integer getStatuscode() {
        return statuscode;
    }

    public void setStatuscode(Integer statuscode) {
        this.statuscode = statuscode;
    }

    public Object getMsg() {
        return msg;
    }

    public void setMsg(Object msg) {
        this.msg = msg;
    }

    public Boolean getIsVersionValid() {
        return isVersionValid;
    }

    public void setIsVersionValid(Boolean isVersionValid) {
        this.isVersionValid = isVersionValid;
    }

    public Boolean getIsAppValid() {
        return isAppValid;
    }

    public void setIsAppValid(Boolean isAppValid) {
        this.isAppValid = isAppValid;
    }

    public Integer getCheckID() {
        return checkID;
    }

    public void setCheckID(Integer checkID) {
        this.checkID = checkID;
    }

    public Boolean getIsPasswordExpired() {
        return isPasswordExpired;
    }

    public void setIsPasswordExpired(Boolean isPasswordExpired) {
        this.isPasswordExpired = isPasswordExpired;
    }

    public Object getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(Object mobileNo) {
        this.mobileNo = mobileNo;
    }

    public Object getEmailID() {
        return emailID;
    }

    public void setEmailID(Object emailID) {
        this.emailID = emailID;
    }

    public Boolean getIsLookUpFromAPI() {
        return isLookUpFromAPI;
    }

    public void setIsLookUpFromAPI(Boolean isLookUpFromAPI) {
        this.isLookUpFromAPI = isLookUpFromAPI;
    }

    public Boolean getIsDTHInfoCall() {
        return isDTHInfoCall;
    }

    public void setIsDTHInfoCall(Boolean isDTHInfoCall) {
        this.isDTHInfoCall = isDTHInfoCall;
    }

    public Boolean getIsShowPDFPlan() {
        return isShowPDFPlan;
    }

    public void setIsShowPDFPlan(Boolean isShowPDFPlan) {
        this.isShowPDFPlan = isShowPDFPlan;
    }

    public Object getSID() {
        return sID;
    }

    public void setSID(Object sID) {
        this.sID = sID;
    }

    public Boolean getIsOTPRequired() {
        return isOTPRequired;
    }

    public void setIsOTPRequired(Boolean isOTPRequired) {
        this.isOTPRequired = isOTPRequired;
    }

    public Integer getGetID() {
        return getID;
    }

    public void setGetID(Integer getID) {
        this.getID = getID;
    }

    public Boolean getIsDTHInfo() {
        return isDTHInfo;
    }

    public void setIsDTHInfo(Boolean isDTHInfo) {
        this.isDTHInfo = isDTHInfo;
    }

    public Boolean getIsRoffer() {
        return isRoffer;
    }

    public void setIsRoffer(Boolean isRoffer) {
        this.isRoffer = isRoffer;
    }
}
