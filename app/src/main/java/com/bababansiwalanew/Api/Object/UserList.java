package com.bababansiwalanew.Api.Object;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserList {
    @SerializedName("rental")
    @Expose
    public String rental;
    @SerializedName("rentalAmt")
    @Expose
    public Integer rentalAmt;
    @SerializedName("capping")
    @Expose
    public Integer capping;
    @SerializedName("referalID")
    @Expose
    public Integer referalID;
    @SerializedName("roleID")
    @Expose
    public Integer roleID;
    @SerializedName("commRate")
    @Expose
    public Double commRate;
    @SerializedName("id")
    @Expose
    public Integer id;
    @SerializedName("role")
    @Expose
    public String role;
    @SerializedName("kycStatus")
    @Expose
    public String kycStatusStr;
    @SerializedName("kycStatus_")
    @Expose
    public Integer kycStatus;
    @SerializedName("outletName")
    @Expose
    public String outletName;
    @SerializedName("mobileNo")
    @Expose
    public String mobileNo;
    @SerializedName("status")
    @Expose
    public Boolean status;
    @SerializedName("isOTP")
    @Expose
    public Boolean isOTP;
    @SerializedName("joinDate")
    @Expose
    public String joinDate;
    @SerializedName("joinBy")
    @Expose
    public String joinBy;
    @SerializedName("slab")
    @Expose
    public String slab;
    @SerializedName("websiteName")
    @Expose
    public String websiteName;
    @SerializedName("balance")
    @Expose
    public String balance;
    @SerializedName("name")
    @Expose
    public Object name;
    @SerializedName("prefix")
    @Expose
    public String prefix;

    public String getRental() {
        return rental;
    }

    public Integer getRentalAmt() {
        return rentalAmt;
    }

    public Integer getCapping() {
        return capping;
    }

    public Integer getReferalID() {
        return referalID;
    }

    public Integer getRoleID() {
        return roleID;
    }

    public Double getCommRate() {
        return commRate;
    }

    public Integer getId() {
        return id;
    }

    public String getRole() {
        return role;
    }

    public Integer getKycStatus() {
        return kycStatus;
    }

    public String getKycStatusStr() {
        return kycStatusStr;
    }

    public String getOutletName() {
        return outletName;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public Boolean getStatus() {
        return status;
    }

    public Boolean getOTP() {
        return isOTP;
    }

    public String getJoinDate() {
        return joinDate;
    }

    public String getJoinBy() {
        return joinBy;
    }

    public String getSlab() {
        return slab;
    }

    public String getWebsiteName() {
        return websiteName;
    }

    public String getBalance() {
        return balance;
    }

    public Object getName() {
        return name;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
}
