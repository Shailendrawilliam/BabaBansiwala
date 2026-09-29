package com.bababansiwalanew.Util;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RazorpayresponseUpdateRequest  {
    @SerializedName("RazorPaySuccess")
    @Expose
    public RazorpayresponseUpdate razorCallbackResp;
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



    public RazorpayresponseUpdateRequest(RazorpayresponseUpdate razorpayresponseUpdate/*,
                                         String app_id,
                                         String imei,
                                         String sessionID*/

    ) {
        this.razorCallbackResp = razorpayresponseUpdate;
       // this.sessionID = sessionID;
      //  this.appid = app_id;
     //   this.imei = imei;

    }
}
