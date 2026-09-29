package com.bababansiwalanew.Api.Object;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserDaybookReport {
    @SerializedName("api")
    @Expose
    public String api;
    @SerializedName("oid")
    @Expose
    public String oid;
    @SerializedName("operator")
    @Expose
    public String operator;
    @SerializedName("totalHits")
    @Expose
    public String totalHits;
    @SerializedName("totalAmount")
    @Expose
    public String totalAmount;
    @SerializedName("successHits")
    @Expose
    public String successHits;
    @SerializedName("successAmount")
    @Expose
    public String successAmount;
    @SerializedName("failedHits")
    @Expose
    public String failedHits;
    @SerializedName("failedAmount")
    @Expose
    public String failedAmount;
    @SerializedName("pendingHits")
    @Expose
    public String pendingHits;
    @SerializedName("pendingAmount")
    @Expose
    public String pendingAmount;
    @SerializedName("apiCommission")
    @Expose
    public String apiCommission;
    @SerializedName("commission")
    @Expose
    public String commission;
    @SerializedName("profit")
    @Expose
    public String profit;
    @SerializedName("gstTaxAmount")
    @Expose
    public String gstTaxAmount;
    @SerializedName("tdsAmount")
    @Expose
    public String tdsAmount;
    @SerializedName("tDate")
    @Expose
    public String tDate;

    public String getApi() {
        return api;
    }

    public String getOid() {
        return oid;
    }

    public String getOperator() {
        return operator;
    }

    public String getTotalHits() {
        return totalHits;
    }

    public String getTotalAmount() {
        return totalAmount;
    }

    public String getSuccessHits() {
        return successHits;
    }

    public String getSuccessAmount() {
        return successAmount;
    }

    public String getFailedHits() {
        return failedHits;
    }

    public String getFailedAmount() {
        return failedAmount;
    }

    public String getPendingHits() {
        return pendingHits;
    }

    public String getPendingAmount() {
        return pendingAmount;
    }

    public String getApiCommission() {
        return apiCommission;
    }

    public String getCommission() {
        return commission;
    }

    public String getProfit() {
        return profit;
    }

    public String getGstTaxAmount() {
        return gstTaxAmount;
    }

    public String getTdsAmount() {
        return tdsAmount;
    }

    public String gettDate() {
        return tDate;
    }
}
