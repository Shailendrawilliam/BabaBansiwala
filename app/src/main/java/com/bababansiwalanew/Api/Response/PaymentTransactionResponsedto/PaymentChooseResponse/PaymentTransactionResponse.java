package com.bababansiwalanew.Api.Response.PaymentTransactionResponsedto.PaymentChooseResponse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.bababansiwalanew.Api.Object.PGModelForApp;


public class PaymentTransactionResponse {


    @SerializedName("pGModelForApp")
    @Expose
    private PGModelForApp pGModelForApp;
    @SerializedName("Statuscode")
    @Expose
    private String statuscode;
    @SerializedName("Msg")
    @Expose
    private String msg;
    @SerializedName("IsVersionValid")
    @Expose
    private String isVersionValid;
    @SerializedName("IsAppValid")
    @Expose
    private String isAppValid;
    @SerializedName("CheckID")
    @Expose
    private String checkID;
    @SerializedName("IsPasswordExpired")
    @Expose
    private String isPasswordExpired;
    @SerializedName("MobileNo")
    @Expose
    private String mobileNo;
    @SerializedName("EmailID")
    @Expose
    private String emailID;
    @SerializedName("IsLookUpFromAPI")
    @Expose
    private String isLookUpFromAPI;
    @SerializedName("IsDTHInfoCall")
    @Expose
    private String isDTHInfoCall;
    @SerializedName("IsShowPDFPlan")
    @Expose
    private String isShowPDFPlan;
    @SerializedName("SID")
    @Expose
    private String sID;
    @SerializedName("IsOTPRequired")
    @Expose
    private String isOTPRequired;
    @SerializedName("GetID")
    @Expose
    private String getID;
    @SerializedName("IsDTHInfo")
    @Expose
    private String isDTHInfo;
    @SerializedName("IsRoffer")
    @Expose
    private String isRoffer;

    public PGModelForApp getPGModelForApp() {
        return pGModelForApp;
    }

    public void setPGModelForApp(PGModelForApp pGModelForApp) {
        this.pGModelForApp = pGModelForApp;
    }

    public String getStatuscode() {
        return statuscode;
    }

    public void setStatuscode(String statuscode) {
        this.statuscode = statuscode;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getIsVersionValid() {
        return isVersionValid;
    }

    public void setIsVersionValid(String isVersionValid) {
        this.isVersionValid = isVersionValid;
    }

    public String getIsAppValid() {
        return isAppValid;
    }

    public void setIsAppValid(String isAppValid) {
        this.isAppValid = isAppValid;
    }

    public String getCheckID() {
        return checkID;
    }

    public void setCheckID(String checkID) {
        this.checkID = checkID;
    }

    public String getIsPasswordExpired() {
        return isPasswordExpired;
    }

    public void setIsPasswordExpired(String isPasswordExpired) {
        this.isPasswordExpired = isPasswordExpired;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getEmailID() {
        return emailID;
    }

    public void setEmailID(String emailID) {
        this.emailID = emailID;
    }

    public String getIsLookUpFromAPI() {
        return isLookUpFromAPI;
    }

    public void setIsLookUpFromAPI(String isLookUpFromAPI) {
        this.isLookUpFromAPI = isLookUpFromAPI;
    }

    public String getIsDTHInfoCall() {
        return isDTHInfoCall;
    }

    public void setIsDTHInfoCall(String isDTHInfoCall) {
        this.isDTHInfoCall = isDTHInfoCall;
    }

    public String getIsShowPDFPlan() {
        return isShowPDFPlan;
    }

    public void setIsShowPDFPlan(String isShowPDFPlan) {
        this.isShowPDFPlan = isShowPDFPlan;
    }

    public String getSID() {
        return sID;
    }

    public void setSID(String sID) {
        this.sID = sID;
    }

    public String getIsOTPRequired() {
        return isOTPRequired;
    }

    public void setIsOTPRequired(String isOTPRequired) {
        this.isOTPRequired = isOTPRequired;
    }

    public String getGetID() {
        return getID;
    }

    public void setGetID(String getID) {
        this.getID = getID;
    }

    public String getIsDTHInfo() {
        return isDTHInfo;
    }

    public void setIsDTHInfo(String isDTHInfo) {
        this.isDTHInfo = isDTHInfo;
    }

    public String getIsRoffer() {
        return isRoffer;
    }

    public void setIsRoffer(String isRoffer) {
        this.isRoffer = isRoffer;
    }

}

