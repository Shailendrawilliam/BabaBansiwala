package com.bababansiwalanew.DMR.ui;
 
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ValidateBioMetricRequest{
    @SerializedName("UserId")
    @Expose
    public int UserId;
    @SerializedName("UMobile")
    @Expose
    public String UMobile;
    @SerializedName("SenderMobile")
    @Expose
    public String SenderMobile;

    @SerializedName("AadhaarNumber")
    @Expose
    public String AadhaarNumber ;

    @SerializedName("PIDDATA")
    @Expose
    public String PIDDATA ;
    @SerializedName("Lattitude")
    @Expose
    public String Lattitude;
    @SerializedName("Longitude")
    @Expose
    public String Longitude ;
    @SerializedName(value = "KycType")
    @Expose
    public String KycType ;

    public String getKycType() {
        return KycType;
    }

    public void setKycType(String KycType) {
        KycType = KycType;
    }

    public ValidateBioMetricRequest(int userId, String UMobile, String senderMobile, String aadhaarNumber, String PIDDATA, String lattitude, String longitude, String KycType) {
        this.UserId = userId;
        this.UMobile = UMobile;
        this.SenderMobile = senderMobile;
        this.AadhaarNumber = aadhaarNumber;
        this.PIDDATA = PIDDATA;
        this.Lattitude = lattitude;
        this.Longitude = longitude;
        this.KycType = KycType;
    }

    public int getUserId() {
        return UserId;
    }

    public void setUserId(int userId) {
        UserId = userId;
    }

    public String getUMobile() {
        return UMobile;
    }

    public void setUMobile(String UMobile) {
        this.UMobile = UMobile;
    }

    public String getSenderMobile() {
        return SenderMobile;
    }

    public void setSenderMobile(String senderMobile) {
        SenderMobile = senderMobile;
    }

    public String getAadhaarNumber() {
        return AadhaarNumber;
    }

    public void setAadhaarNumber(String aadhaarNumber) {
        AadhaarNumber = aadhaarNumber;
    }

    public String getPIDDATA() {
        return PIDDATA;
    }

    public void setPIDDATA(String PIDDATA) {
        this.PIDDATA = PIDDATA;
    }

    public String getLattitude() {
        return Lattitude;
    }

    public void setLattitude(String lattitude) {
        Lattitude = lattitude;
    }

    public String getLongitude() {
        return Longitude;
    }

    public void setLongitude(String longitude) {
        Longitude = longitude;
    }

}