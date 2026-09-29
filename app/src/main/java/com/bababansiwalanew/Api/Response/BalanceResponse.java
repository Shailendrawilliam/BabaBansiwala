package com.bababansiwalanew.Api.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.bababansiwalanew.Api.Object.BalanceData;

public class BalanceResponse {
    @SerializedName("data")
    @Expose
    public BalanceData balanceData;
    @SerializedName("statuscode")
    @Expose
    public Integer statuscode;
    @SerializedName("msg")
    @Expose
    public String msg;
    @SerializedName("isVersionValid")
    @Expose
    public Boolean isVersionValid;
    @SerializedName("isAppValid")
    @Expose
    public Boolean isAppValid;
    @SerializedName("checkID")
    @Expose
    public Integer checkID;
    @SerializedName("isPasswordExpired")
    @Expose
    public Boolean isPasswordExpired;

    @SerializedName("isLookUpFromAPI")
    @Expose
    public Boolean isLookUpFromAPI;

    public Boolean getLookUpFromAPI() {
        return isLookUpFromAPI;
    }

    public void setLookUpFromAPI(Boolean lookUpFromAPI) {
        isLookUpFromAPI = lookUpFromAPI;
    }

    public BalanceData getBalanceData() {
        return balanceData;
    }

    public Integer getStatuscode() {
        return statuscode;
    }

    public String getMsg() {
        return msg;
    }

    public Boolean getVersionValid() {
        return isVersionValid;
    }

    public Boolean getAppValid() {
        return isAppValid;
    }

    public Integer getCheckID() {
        return checkID;
    }

    public Boolean getPasswordExpired() {
        return isPasswordExpired;
    }
}
