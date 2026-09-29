package com.bababansiwalanew.Api.Object;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FundRequestForApproval {
    @SerializedName("isSelf")
    @Expose
    public Boolean isSelf;
    @SerializedName("paymentId")
    @Expose
    public Integer paymentId;
    @SerializedName("userId")
    @Expose
    public Integer userId;
    @SerializedName("lt")
    @Expose
    public Integer lt;
    @SerializedName("bank")
    @Expose
    public String bank;
    @SerializedName("accountNo")
    @Expose
    public String accountNo;
    @SerializedName("mode")
    @Expose
    public String mode;
    @SerializedName("transactionId")
    @Expose
    public String transactionId;
    @SerializedName("mobileNo")
    @Expose
    public String mobileNo;
    @SerializedName("chequeNo")
    @Expose
    public String chequeNo;
    @SerializedName("accountHolder")
    @Expose
    public String accountHolder;
    @SerializedName("cardNumber")
    @Expose
    public String cardNumber;
    @SerializedName("entryDate")
    @Expose
    public String entryDate;
    @SerializedName("amount")
    @Expose
    public String amount;
    @SerializedName("status")
    @Expose
    public String status;
    @SerializedName("statusCode")
    @Expose
    public Integer statusCode;
    @SerializedName("description")
    @Expose
    public String description;
    @SerializedName("userName")
    @Expose
    public String userName;
    @SerializedName("userMobile")
    @Expose
    public String userMobile;
    @SerializedName("toDate")
    @Expose
    public String toDate;
    @SerializedName("_TMode")
    @Expose
    public Integer tMode;
    @SerializedName("commRate")
    @Expose
    public Double commRate;
    @SerializedName("remark")
    @Expose
    public String remark;

    public Boolean getSelf() {
        return isSelf;
    }

    public Integer getPaymentId() {
        return paymentId;
    }

    public Integer getUserId() {
        return userId;
    }

    public Integer getLt() {
        return lt;
    }

    public String getBank() {
        return bank;
    }

    public String getAccountNo() {
        return accountNo;
    }

    public String getMode() {
        return mode;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public String getChequeNo() {
        return chequeNo;
    }

    public String getAccountHolder() {
        return accountHolder;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public String getEntryDate() {
        return entryDate;
    }

    public String getAmount() {
        return amount;
    }

    public String getStatus() {
        return status;
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    public String getDescription() {
        return description;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserMobile() {
        return userMobile;
    }

    public String getToDate() {
        return toDate;
    }

    public Integer gettMode() {
        return tMode;
    }

    public Double getCommRate() {
        return commRate;
    }

    public String getRemark() {
        return remark;
    }
}
