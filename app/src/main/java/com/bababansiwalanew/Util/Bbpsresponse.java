package com.bababansiwalanew.Util;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Bbpsresponse {
    @SerializedName("Statuscode")
    @Expose
    private Integer statuscode;
    @SerializedName("Msg")
    @Expose
    private Object msg;
    @SerializedName("ErrorCode")
    @Expose
    private Object errorCode;
    @SerializedName("ErrorMsg")
    @Expose
    private Object errorMsg;
    @SerializedName("IsEditable")
    @Expose
    private Boolean isEditable;
    @SerializedName("IsEnablePayment")
    @Expose
    private Boolean isEnablePayment;
    @SerializedName("IsShowMsgOnly")
    @Expose
    private Boolean isShowMsgOnly;
    @SerializedName("IsHardCoded")
    @Expose
    private Boolean isHardCoded;
    @SerializedName("CustomerName")
    @Expose
    private String customerName;
    @SerializedName("BillNumber")
    @Expose
    private Object billNumber;
    @SerializedName("BillDate")
    @Expose
    private String billDate;
    @SerializedName("DueDate")
    @Expose
    private String dueDate;
    @SerializedName("Amount")
    @Expose
    private String amount;
    @SerializedName("BillPeriod")
    @Expose
    private Object billPeriod;
    @SerializedName("RefferenceID")
    @Expose
    private Object refferenceID;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("billamount")
    @Expose
    private String billamount;

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

    public Object getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(Object errorCode) {
        this.errorCode = errorCode;
    }

    public Object getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(Object errorMsg) {
        this.errorMsg = errorMsg;
    }

    public Boolean getIsEditable() {
        return isEditable;
    }

    public void setIsEditable(Boolean isEditable) {
        this.isEditable = isEditable;
    }

    public Boolean getIsEnablePayment() {
        return isEnablePayment;
    }

    public void setIsEnablePayment(Boolean isEnablePayment) {
        this.isEnablePayment = isEnablePayment;
    }

    public Boolean getIsShowMsgOnly() {
        return isShowMsgOnly;
    }

    public void setIsShowMsgOnly(Boolean isShowMsgOnly) {
        this.isShowMsgOnly = isShowMsgOnly;
    }

    public Boolean getIsHardCoded() {
        return isHardCoded;
    }

    public void setIsHardCoded(Boolean isHardCoded) {
        this.isHardCoded = isHardCoded;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public Object getBillNumber() {
        return billNumber;
    }

    public void setBillNumber(Object billNumber) {
        this.billNumber = billNumber;
    }

    public String getBillDate() {
        return billDate;
    }

    public void setBillDate(String billDate) {
        this.billDate = billDate;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public Object getBillPeriod() {
        return billPeriod;
    }

    public void setBillPeriod(Object billPeriod) {
        this.billPeriod = billPeriod;
    }

    public Object getRefferenceID() {
        return refferenceID;
    }

    public void setRefferenceID(Object refferenceID) {
        this.refferenceID = refferenceID;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getBillamount() {
        return billamount;
    }

    public void setBillamount(String billamount) {
        this.billamount = billamount;
    }

}
