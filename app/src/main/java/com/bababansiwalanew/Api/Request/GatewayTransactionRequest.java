package com.bababansiwalanew.Api.Request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GatewayTransactionRequest {

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
    @SerializedName("Amount")
    @Expose
    private String amount;
    @SerializedName("UPGID")
    @Expose
    private String uPGID;

    @SerializedName("WalletID")
    @Expose
    private String WalletID;
    @SerializedName("OID")
    @Expose
    private int oID;
    @SerializedName("version")
    @Expose
    private String version;

    public String getuMobile() {
        return uMobile;
    }

    public void setuMobile(String uMobile) {
        this.uMobile = uMobile;
    }

    public String getuPGID() {
        return uPGID;
    }

    public void setuPGID(String uPGID) {
        this.uPGID = uPGID;
    }

    public String getWalletID() {
        return WalletID;
    }

    public void setWalletID(String walletID) {
        WalletID = walletID;
    }

    public int getoID() {
        return oID;
    }

    public void setoID(int oID) {
        this.oID = oID;
    }

    public GatewayTransactionRequest(String WalletID, String appid, String imei, String session, String userID, String uMobile, String amount, String uPGID, int oID, String version) {
        this.appid = appid;
        this.imei = imei;
        this.session = session;
        this.userID = userID;
        this.uMobile = uMobile;
        this.amount = amount;
        this.uPGID = uPGID;
        this.oID = oID;
        this.version = version;
        this.WalletID = WalletID;
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

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getUPGID() {
        return uPGID;
    }

    public void setUPGID(String uPGID) {
        this.uPGID = uPGID;
    }

    public int getOID() {
        return oID;
    }

    public void setOID(int oID) {
        this.oID = oID;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

}
