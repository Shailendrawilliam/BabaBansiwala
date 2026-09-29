package com.bababansiwalanew.FundRecReport.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Lalit on 15-04-2017.
 */

public class FundRecObject {
    @SerializedName("TID")
    @Expose
    private String TID;
    @SerializedName("BalanceAmount")
    @Expose
    private String BalanceAmount;
    @SerializedName("From")
    @Expose
    private String From;
    @SerializedName("Amount")
    @Expose
    private String Amount;
    @SerializedName("DateTime")
    @Expose
    private String DateTime;
    @SerializedName("To")
    @Expose
    private Object To;
    private String Column1;

    private String TransactionID;

    private String BalanceAmt;
    private String MobileNo;
    private String Name;
    private String Amont;
    private String FundDate;

    public String getMobileNo() {
        return MobileNo;
    }

    public void setMobileNo(String mobileNo) {
        MobileNo = mobileNo;
    }

     public String getBalanceAmount() {
        return BalanceAmount;
    }

    public void setBalanceAmount(String BalanceAmount) {
        this.BalanceAmount = BalanceAmount;
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

    public String getFundDate() {
        return FundDate;
    }

    public void setFundDate(String fundDate) {
        FundDate = fundDate;
    }

    public String getColumn1() {
        return Column1;
    }

    public void setColumn1(String column1) {
        Column1 = column1;
    }

    public String getTID() {
        return TID;
    }

    public void setTID(String TID) {
        this.TID = TID;
    }

    public String getFrom() {
        return From;
    }

    public void setFrom(String from) {
        From = from;
    }

    public String getAmount() {
        return Amount;
    }

    public void setAmount(String amount) {
        Amount = amount;
    }

    public String getDateTime() {
        return DateTime;
    }

    public void setDateTime(String dateTime) {
        DateTime = dateTime;
    }

    public String getTransactionID() {
        return TransactionID;
    }

    public void setTransactionID(String transactionID) {
        TransactionID = transactionID;
    }

    public String getTo() {
        return String.valueOf(To);
    }

    public void setTo(String to) {
        To = to;
    }

    public String getBalanceAmt() {
        return BalanceAmt;
    }

    public void setBalanceAmt(String balanceAmt) {
        BalanceAmt = balanceAmt;
    }
}
