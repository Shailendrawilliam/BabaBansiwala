package com.bababansiwalanew.DMR.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoginSenderResponse {
    private DMR DMR;
    @SerializedName("response_status_id")
    @Expose
    private Integer responseStatusId;
    @SerializedName("data")
    @Expose
    private DmrData data;
    @SerializedName("response_type_id")
    @Expose
    private Integer responseTypeId;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("SenderMemberID")
    @Expose
    private String senderMemberID;
    @SerializedName("OtpReff")
    @Expose
    private String OtpReff;
    @SerializedName("IsOTPGenerated")
    @Expose
    private boolean IsOTPGenerated;
    @SerializedName("IsSenderRegOTPRequired")
    @Expose
    private boolean IsSenderRegOTPRequired;
    @SerializedName("IsSenderRegRequired")
    @Expose
    private boolean IsSenderRegRequired;
    @SerializedName("IsBiomatricRequired")
    @Expose
    private boolean IsBiomatricRequired;
    @SerializedName("ReffId")
    @Expose
    private String ReffId;
    @SerializedName("IsOTP")
    @Expose
    private boolean IsOTP;

    public boolean getIsBiomatricRequired() {
        return IsBiomatricRequired;
    }

    public void setIsBiomatricRequired(boolean isBiomatricRequired) {
        IsBiomatricRequired = isBiomatricRequired;
    }

    public String getReffId() {
        return ReffId;
    }

    public void setReffId(String reffId) {
        ReffId = reffId;
    }

    public String getOtpReff() {
        return OtpReff;
    }

    public void setOtpReff(String otpReff) {
        OtpReff = otpReff;
    }

    public Integer getResponseStatusId() {
        return responseStatusId;
    }

    public void setResponseStatusId(Integer responseStatusId) {
        this.responseStatusId = responseStatusId;
    }

    public DmrData getData() {
        return data;
    }

    public void setData(DmrData data) {
        this.data = data;
    }

    public Integer getResponseTypeId() {
        return responseTypeId;
    }

    public void setResponseTypeId(Integer responseTypeId) {
        this.responseTypeId = responseTypeId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getSenderMemberID() {
        return senderMemberID;
    }

    public void setSenderMemberID(String senderMemberID) {
        this.senderMemberID = senderMemberID;
    }

    public DMR getDMR() {
        return DMR;
    }

    public void setDMR(DMR DMR) {
        this.DMR = DMR;
    }

    public boolean isOTPGenerated() {
        return IsOTPGenerated;
    }

    public void setOTPGenerated(boolean OTPGenerated) {
        IsOTPGenerated = OTPGenerated;
    }

    public boolean isSenderRegOTPRequired() {
        return IsSenderRegOTPRequired;
    }

    public void setSenderRegOTPRequired(boolean senderRegOTPRequired) {
        IsSenderRegOTPRequired = senderRegOTPRequired;
    }

    public boolean isSenderRegRequired() {
        return IsSenderRegRequired;
    }

    public void setSenderRegRequired(boolean senderRegRequired) {
        IsSenderRegRequired = senderRegRequired;
    }

    public boolean isBiomatricRequired() {
        return IsBiomatricRequired;
    }

    public void setBiomatricRequired(boolean biomatricRequired) {
        IsBiomatricRequired = biomatricRequired;
    }

    public boolean isOTP() {
        return IsOTP;
    }

    public void setOTP(boolean OTP) {
        IsOTP = OTP;
    }
}
