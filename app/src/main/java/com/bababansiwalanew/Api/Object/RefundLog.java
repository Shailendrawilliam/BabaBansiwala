package com.bababansiwalanew.Api.Object;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RefundLog {
    @SerializedName("tid")
    @Expose
    public Integer tid;
    @SerializedName("transactionID")
    @Expose
    public String transactionID;

    @SerializedName("_RefundType")
    @Expose
    public Integer _refundType;
    @SerializedName("refundType_")
    @Expose
    public String refundType;
    @SerializedName("accountNo")
    @Expose
    public String accountNo;
    @SerializedName("requestedAmount")
    @Expose
    public Integer requestedAmount;
    @SerializedName("entryDate")
    @Expose
    public String entryDate;
    @SerializedName("refundRequestDate")
    @Expose
    public String refundRequestDate;
    @SerializedName("refundActionDate")
    @Expose
    public String refundActionDate;
    @SerializedName("apiid")
    @Expose
    public Integer apiid;
    @SerializedName("apiName")
    @Expose
    public String apiName;
    @SerializedName("vendorID")
    @Expose
    public String vendorID;
    @SerializedName("operator")
    @Expose
    public String operator;
    @SerializedName("oid")
    @Expose
    public Integer oid;
    @SerializedName("outletName")
    @Expose
    public String outletName;
    @SerializedName("outletMobile")
    @Expose
    public String outletMobile;
    @SerializedName("response")
    @Expose
    public Object response;
    @SerializedName("rStatus")
    @Expose
    public String rStatus;
    @SerializedName("refundRemark")
    @Expose
    public String refundRemark;
    @SerializedName("liveID")
    @Expose
    public String liveID;
    @SerializedName("rightAccountNo")
    @Expose
    public Object rightAccountNo;

    public Integer getTid() {
        return tid;
    }

    public String getTransactionID() {
        return transactionID;
    }

    public Integer get_refundType() {
        return _refundType;
    }

    public String getRefundType() {
        return refundType;
    }

    public String getAccountNo() {
        return accountNo;
    }

    public Integer getRequestedAmount() {
        return requestedAmount;
    }

    public String getEntryDate() {
        return entryDate;
    }

    public String getRefundRequestDate() {
        return refundRequestDate;
    }

    public String getRefundActionDate() {
        return refundActionDate;
    }

    public Integer getApiid() {
        return apiid;
    }

    public String getApiName() {
        return apiName;
    }

    public String getVendorID() {
        return vendorID;
    }

    public String getOperator() {
        return operator;
    }

    public Integer getOid() {
        return oid;
    }

    public String getOutletName() {
        return outletName;
    }

    public String getOutletMobile() {
        return outletMobile;
    }

    public Object getResponse() {
        return response;
    }

    public String getrStatus() {
        return rStatus;
    }

    public String getRefundRemark() {
        return refundRemark;
    }

    public String getLiveID() {
        return liveID;
    }

    public Object getRightAccountNo() {
        return rightAccountNo;
    }
}
