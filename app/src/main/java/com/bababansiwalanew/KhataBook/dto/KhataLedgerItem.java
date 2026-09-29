package com.bababansiwalanew.KhataBook.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class KhataLedgerItem implements Serializable {

    @SerializedName(value = "Id", alternate = {"ID", "TransactionId", "id", "transId"})
    @Expose
    private String id;

    @SerializedName(value = "Amount", alternate = {"AMOUNT", "amount"})
    @Expose
    private String amount;

    @SerializedName(value = "FundType", alternate = {"FUNDTYPE", "Type", "type", "CrDr", "CRDR", "Status", "STATUS", "fundType"})
    @Expose
    private String fundType;

    @SerializedName(value = "Remark", alternate = {"DESCRIPTION", "Description", "remark", "Note", "note", "desc"})
    @Expose
    private String remark;

    @SerializedName(value = "CreatedDate", alternate = {"Date", "EntryDate", "TDATE", "createdDate", "date"})
    @Expose
    private String createdDate;

    @SerializedName(value = "BalanceAmount", alternate = {"BALANCEAMOUNT", "Balance", "balance", "balanceAmount"})
    @Expose
    private String balanceAmount;

    @SerializedName(value = "ImageName", alternate = {"Image", "IMAGE", "ImageUrl", "image", "imageUrl", "Img", "img", "BillImage", "billImage", "_Image"})
    @Expose
    private String imageName;

    public KhataLedgerItem() {
    }

    public String getImageName() {
        return imageName != null ? imageName : "";
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public String getImage() {
        return getImageName();
    }

    public void setImage(String image) {
        this.imageName = image;
    }

    public String getId() {
        return id != null ? id : "";
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAmount() {
        return amount != null ? amount : "0";
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getFundType() {
        return fundType != null ? fundType : "";
    }

    public void setFundType(String fundType) {
        this.fundType = fundType;
    }

    public String getRemark() {
        return remark != null ? remark : "";
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getCreatedDate() {
        return createdDate != null ? createdDate : "";
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String getBalanceAmount() {
        return balanceAmount != null ? balanceAmount : "0";
    }

    public void setBalanceAmount(String balanceAmount) {
        this.balanceAmount = balanceAmount;
    }

    public boolean isCredit() {
        if (fundType == null) return false;
        String lower = fundType.toLowerCase();
        return lower.contains("credit") || lower.contains("cr") || lower.contains("got") || lower.contains("received");
    }
}
