package com.bababansiwalanew.Api.Object;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RequestPTM {

    @SerializedName("MID")
    @Expose
    private String mID;
    @SerializedName("ORDER_ID")
    @Expose
    private String oRDERID;
    @SerializedName("CUST_ID")
    @Expose
    private String cUSTID;
    @SerializedName("TXN_AMOUNT")
    @Expose
    private String tXNAMOUNT;
    @SerializedName("CHANNEL_ID")
    @Expose
    private String cHANNELID;
    @SerializedName("WEBSITE")
    @Expose
    private String wEBSITE;
    @SerializedName("MOBILE_NO")
    @Expose
    private String mOBILENO;
    @SerializedName("EMAIL")
    @Expose
    private String eMAIL;
    @SerializedName("INDUSTRY_TYPE_ID")
    @Expose
    private String iNDUSTRYTYPEID;
    @SerializedName("CALLBACK_URL")
    @Expose
    private String cALLBACKURL;
    @SerializedName("MERC_UNQ_REF")
    @Expose
    private Object mERCUNQREF;
    @SerializedName("PAYMENT_MODE_ONLY")
    @Expose
    private Object pAYMENTMODEONLY;
    @SerializedName("AUTH_MODE")
    @Expose
    private Object aUTHMODE;
    @SerializedName("PAYMENT_TYPE_ID")
    @Expose
    private Object pAYMENTTYPEID;
    @SerializedName("BANK_CODE")
    @Expose
    private Object bANKCODE;
    @SerializedName("CHECKSUMHASH")
    @Expose
    private String cHECKSUMHASH;

    public String getMID() {
        return mID;
    }

    public void setMID(String mID) {
        this.mID = mID;
    }

    public String getORDERID() {
        return oRDERID;
    }

    public void setORDERID(String oRDERID) {
        this.oRDERID = oRDERID;
    }

    public String getCUSTID() {
        return cUSTID;
    }

    public void setCUSTID(String cUSTID) {
        this.cUSTID = cUSTID;
    }

    public String getTXNAMOUNT() {
        return tXNAMOUNT;
    }

    public void setTXNAMOUNT(String tXNAMOUNT) {
        this.tXNAMOUNT = tXNAMOUNT;
    }

    public String getCHANNELID() {
        return cHANNELID;
    }

    public void setCHANNELID(String cHANNELID) {
        this.cHANNELID = cHANNELID;
    }

    public String getWEBSITE() {
        return wEBSITE;
    }

    public void setWEBSITE(String wEBSITE) {
        this.wEBSITE = wEBSITE;
    }

    public String getMOBILENO() {
        return mOBILENO;
    }

    public void setMOBILENO(String mOBILENO) {
        this.mOBILENO = mOBILENO;
    }

    public String getEMAIL() {
        return eMAIL;
    }

    public void setEMAIL(String eMAIL) {
        this.eMAIL = eMAIL;
    }

    public String getINDUSTRYTYPEID() {
        return iNDUSTRYTYPEID;
    }

    public void setINDUSTRYTYPEID(String iNDUSTRYTYPEID) {
        this.iNDUSTRYTYPEID = iNDUSTRYTYPEID;
    }

    public String getCALLBACKURL() {
        return cALLBACKURL;
    }

    public void setCALLBACKURL(String cALLBACKURL) {
        this.cALLBACKURL = cALLBACKURL;
    }

    public Object getMERCUNQREF() {
        return mERCUNQREF;
    }

    public void setMERCUNQREF(Object mERCUNQREF) {
        this.mERCUNQREF = mERCUNQREF;
    }

    public Object getPAYMENTMODEONLY() {
        return pAYMENTMODEONLY;
    }

    public void setPAYMENTMODEONLY(Object pAYMENTMODEONLY) {
        this.pAYMENTMODEONLY = pAYMENTMODEONLY;
    }

    public Object getAUTHMODE() {
        return aUTHMODE;
    }

    public void setAUTHMODE(Object aUTHMODE) {
        this.aUTHMODE = aUTHMODE;
    }

    public Object getPAYMENTTYPEID() {
        return pAYMENTTYPEID;
    }

    public void setPAYMENTTYPEID(Object pAYMENTTYPEID) {
        this.pAYMENTTYPEID = pAYMENTTYPEID;
    }

    public Object getBANKCODE() {
        return bANKCODE;
    }

    public void setBANKCODE(Object bANKCODE) {
        this.bANKCODE = bANKCODE;
    }

    public String getCHECKSUMHASH() {
        return cHECKSUMHASH;
    }

    public void setCHECKSUMHASH(String cHECKSUMHASH) {
        this.cHECKSUMHASH = cHECKSUMHASH;
    }

}



/*
public class RequestPTM {
    String mid;
    String ordeR_ID;
    String cusT_ID;
    String txN_AMOUNT;
    String channeL_ID;
    String website;
    String mobilE_NO;
    String email;
    String industrY_TYPE_ID;
    String callbacK_URL;
    String merC_UNQ_REF;
    String paymenT_MODE_ONLY;
    String autH_MODE;
    String paymenT_TYPE_ID;
    String banK_CODE;
    String checksumhash;

    public String getMid() {
        return mid;
    }

    public String getOrdeR_ID() {
        return ordeR_ID;
    }

    public String getCusT_ID() {
        return cusT_ID;
    }

    public String getTxN_AMOUNT() {
        return txN_AMOUNT;
    }

    public String getChanneL_ID() {
        return channeL_ID;
    }

    public String getWebsite() {
        return website;
    }

    public String getMobilE_NO() {
        return mobilE_NO;
    }

    public String getEmail() {
        return email;
    }

    public String getIndustrY_TYPE_ID() {
        return industrY_TYPE_ID;
    }

    public String getCallbacK_URL() {
        return callbacK_URL;
    }

    public String getMerC_UNQ_REF() {
        return merC_UNQ_REF;
    }

    public String getPaymenT_MODE_ONLY() {
        return paymenT_MODE_ONLY;
    }

    public String getAutH_MODE() {
        return autH_MODE;
    }

    public String getPaymenT_TYPE_ID() {
        return paymenT_TYPE_ID;
    }

    public String getBanK_CODE() {
        return banK_CODE;
    }

    public String getChecksumhash() {
        return checksumhash;
    }
}
*/
