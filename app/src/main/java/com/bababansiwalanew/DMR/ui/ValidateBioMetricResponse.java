package com.bababansiwalanew.DMR.ui;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ValidateBioMetricResponse {

    @SerializedName("response_status_id")
    @Expose
    private Integer responseStatusId;
    @SerializedName("data")
    @Expose
    private Object data;
    @SerializedName("response_type_id")
    @Expose
    private Integer responseTypeId;
    @SerializedName("message")
    @Expose
    private Object message;
    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("SenderMemberID")
    @Expose
    private Object senderMemberID;
    @SerializedName("OtpReff")
    @Expose
    private Object otpReff;
    @SerializedName("OId")
    @Expose
    private Object oId;
    @SerializedName("IsOTP")
    @Expose
    private Boolean isOTP;
    @SerializedName("ReffId")
    @Expose
    private Object reffId;
    @SerializedName("IsBiomatricRequired")
    @Expose
    private Boolean isBiomatricRequired;
    @SerializedName("IsSenderRegRequired")
    @Expose
    private Boolean isSenderRegRequired;
    @SerializedName("IsSenderRegOTPRequired")
    @Expose
    private Boolean isSenderRegOTPRequired;
    @SerializedName("IsOTPGenerated")
    @Expose
    private Boolean isOTPGenerated;

    public Integer getResponseStatusId() {
        return responseStatusId;
    }

    public void setResponseStatusId(Integer responseStatusId) {
        this.responseStatusId = responseStatusId;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Integer getResponseTypeId() {
        return responseTypeId;
    }

    public void setResponseTypeId(Integer responseTypeId) {
        this.responseTypeId = responseTypeId;
    }

    public Object getMessage() {
        return message;
    }

    public void setMessage(Object message) {
        this.message = message;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Object getSenderMemberID() {
        return senderMemberID;
    }

    public void setSenderMemberID(Object senderMemberID) {
        this.senderMemberID = senderMemberID;
    }

    public Object getOtpReff() {
        return otpReff;
    }

    public void setOtpReff(Object otpReff) {
        this.otpReff = otpReff;
    }

    public Object getOId() {
        return oId;
    }

    public void setOId(Object oId) {
        this.oId = oId;
    }

    public Boolean getIsOTP() {
        return isOTP;
    }

    public void setIsOTP(Boolean isOTP) {
        this.isOTP = isOTP;
    }

    public Object getReffId() {
        return reffId;
    }

    public void setReffId(Object reffId) {
        this.reffId = reffId;
    }

    public Boolean getIsBiomatricRequired() {
        return isBiomatricRequired;
    }

    public void setIsBiomatricRequired(Boolean isBiomatricRequired) {
        this.isBiomatricRequired = isBiomatricRequired;
    }

    public Boolean getIsSenderRegRequired() {
        return isSenderRegRequired;
    }

    public void setIsSenderRegRequired(Boolean isSenderRegRequired) {
        this.isSenderRegRequired = isSenderRegRequired;
    }

    public Boolean getIsSenderRegOTPRequired() {
        return isSenderRegOTPRequired;
    }

    public void setIsSenderRegOTPRequired(Boolean isSenderRegOTPRequired) {
        this.isSenderRegOTPRequired = isSenderRegOTPRequired;
    }

    public Boolean getIsOTPGenerated() {
        return isOTPGenerated;
    }

    public void setIsOTPGenerated(Boolean isOTPGenerated) {
        this.isOTPGenerated = isOTPGenerated;
    }

}