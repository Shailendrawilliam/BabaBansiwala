package com.bababansiwalanew.Util;


import com.google.gson.annotations.Expose;
        import com.google.gson.annotations.SerializedName;

public class SendMoneyResponse {
    @SerializedName("response_status_id")
    @Expose
    private Integer responseStatusId;
    @SerializedName("data")
    @Expose
    private Object data;
    @SerializedName("responsef_type_id")
    @Expose
    private Integer responseTypeId;
    @SerializedName("message")
    @Expose
    private String message;

    public String getOtpReff() {
        return otpReff;
    }

    public void setOtpReff(String otpReff) {
        this.otpReff = otpReff;
    }

    @SerializedName(value = "otpReff", alternate = "OtpReff")
    @Expose
    private String otpReff;
    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("SenderMemberID")
    @Expose
    private Object senderMemberID;

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

    public Object getSenderMemberID() {
        return senderMemberID;
    }

    public void setSenderMemberID(Object senderMemberID) {
        this.senderMemberID = senderMemberID;
    }
}
/*

public class SendMoneyResponse {
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
    private String message;
    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("SenderMemberID")
    @Expose
    private Object senderMemberID;

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

    public Object getSenderMemberID() {
        return senderMemberID;
    }

    public void setSenderMemberID(Object senderMemberID) {
        this.senderMemberID = senderMemberID;
    }
}
*/
