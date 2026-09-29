package com.bababansiwalanew.RechargeReport.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Lalit on 10-04-2017.
 */

public class RechargeStatus {
    @SerializedName(value = "RID",alternate = "ID")
    @Expose
    private String ID;
    @SerializedName(value = "RDATE",alternate = "RDate")
    @Expose
    private String RDate;
    @SerializedName("TID")
    @Expose
    private String TID;
    @SerializedName(value = "RECHARGENO",alternate = "RechargeNo")
    @Expose
    private String RechargeNo;
    @SerializedName(value = "AMOUNT",alternate = "Amount")
    @Expose
    private String Amount;
    @SerializedName(value = "BALAMT",alternate = "BalanceAmt")
    @Expose
    private String BalanceAmt;
    @SerializedName(value = "OPID",alternate = "OpID")
    @Expose
    private String OpID;
    @SerializedName(value = "OPNAME",alternate = "OpName")
    @Expose
    private String OpName;
    @SerializedName("Status")
    @Expose
    private String Status;
    @SerializedName(value = "ISDISPUTABLE",alternate = "IsDisputable")
    @Expose
    private String IsDisputable;
    @SerializedName(value = "ICON",alternate = "Column1")
    @Expose
    private String Column1;

    private String MemberId;



    public String getColumn1() {
        return Column1;
    }

    public void setColumn1(String column1) {
        Column1 = column1;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getMemberId() {
        return MemberId;
    }

    public void setMemberId(String memberId) {
        MemberId = memberId;
    }

    public String getTID() {
        return TID;
    }

    public void setTID(String TID) {
        this.TID = TID;
    }

    public String getRechargeNo() {
        return RechargeNo;
    }

    public void setRechargeNo(String rechargeNo) {
        RechargeNo = rechargeNo;
    }

    public String getAmount() {
        return Amount;
    }

    public void setAmount(String amount) {
        Amount = amount;
    }

    public String getBalanceAmt() {
        return BalanceAmt;
    }

    public void setBalanceAmt(String balanceAmt) {
        BalanceAmt = balanceAmt;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public String getRDate() {
        return RDate;
    }

    public void setRDate(String RDate) {
        this.RDate = RDate;
    }

    public String getOpID() {
        return OpID;
    }

    public void setOpID(String opID) {
        OpID = opID;
    }

    public String getOpName() {
        return OpName;
    }

    public void setOpName(String opName) {
        OpName = opName;
    }

    public String getIsDisputable() {
        return IsDisputable;
    }

    public void setIsDisputable(String isDisputable) {
        IsDisputable = isDisputable;
    }
}
