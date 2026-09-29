package com.bababansiwalanew.Api.Request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ChoosePaymentGatewayRequest {

    @SerializedName("appid")
    @Expose
    private String appid;
    @SerializedName("imei")
    @Expose
    private String imei;
    @SerializedName("session")
    @Expose
    private String session;
    @SerializedName("userID")
    @Expose
    private String userID;
    @SerializedName("UMobile")
    @Expose
    private String uMobile;
    @SerializedName("version")
    @Expose
    private String version;

    public ChoosePaymentGatewayRequest(String appid, String imei, String session, String userID, String uMobile, String version) {
        this.appid = appid;
        this.imei = imei;
        this.session = session;
        this.userID = userID;
        this.uMobile = uMobile;
        this.version = version;
    }

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public String getSession() {
        return session;
    }

    public void setSession(String session) {
        this.session = session;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getUMobile() {
        return uMobile;
    }

    public void setUMobile(String uMobile) {
        this.uMobile = uMobile;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

}
