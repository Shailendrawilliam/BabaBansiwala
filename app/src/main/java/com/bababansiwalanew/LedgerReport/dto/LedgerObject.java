package com.bababansiwalanew.LedgerReport.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Lalit on 10-04-2017.
 */

public class LedgerObject {
    @SerializedName(value = "DESCRIPTION",alternate="Remark")
    @Expose
    private String Remark;
    @SerializedName(value = "STATUS",alternate = "Status")
    @Expose
    private String Status;
    @SerializedName(value = "AMOUNT",alternate = "Amount")
    @Expose
    private String Amount;
    @SerializedName(value = "BALANCEAMOUNT",alternate = "BalanceAmount")
    @Expose
    private String BalanceAmount;
    @SerializedName(value = "TDATE",alternate = "CreatedDate")
    @Expose
    private String CreatedDate;
    @SerializedName("Utiltibalance")
    @Expose
    private String utiltibalance;

    private String Column1;



    public String getColumn1() {
        return Column1;
    }

    public void setColumn1(String column1) {
        Column1 = column1;
    }

    public String getRemark() {
        return Remark;
    }

    public void setRemark(String remark) {
        Remark = remark;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public String getAmount() {
        return Amount;
    }

    public void setAmount(String amount) {
        Amount = amount;
    }

    public String getBalanceAmount() {
        return BalanceAmount;
    }

    public void setBalanceAmount(String balanceAmount) {
        BalanceAmount = balanceAmount;
    }

    public String getCreatedDate() {
        return CreatedDate;
    }

    public void setCreatedDate(String createdDate) {
        CreatedDate = createdDate;
    }
}
