package com.bababansiwalanew.Api.Request;

import com.google.gson.JsonObject;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PayTMTransactionUpdateRequest {


    @SerializedName("PaytmCallbackResp")
    @Expose
    public JsonObject paytmCallbackResp;
    @SerializedName("userID")
    @Expose
    public String userID;
    @SerializedName("sessionID")
    @Expose
    public String sessionID;
    @SerializedName("session")
    @Expose
    public String session;
    @SerializedName("appid")
    @Expose
    public String appid;
    @SerializedName("imei")
    @Expose
    public String imei;
    @SerializedName("regKey")
    @Expose
    public String regKey;
    @SerializedName("version")
    @Expose
    public String version;
    @SerializedName("serialNo")
    @Expose
    public String serialNo;
    @SerializedName("loginTypeID")
    @Expose
    public String loginTypeID;

    @SerializedName("UMobile")
    @Expose
    private String uMobile;


    public PayTMTransactionUpdateRequest(JsonObject paytmCallbackResp, String userID, String loginTypeID, String appid, String imei, String regKey, String version, String serialNo, String sessionID, String session, String uMobile) {
        this.paytmCallbackResp = paytmCallbackResp;
        this.userID = userID;
        this.sessionID = sessionID;
        this.session = session;
        this.appid = appid;
        this.imei = imei;
        this.regKey = regKey;
        this.version = version;
        this.serialNo = serialNo;
        this.loginTypeID = loginTypeID;
        this.uMobile = uMobile;
    }
}
