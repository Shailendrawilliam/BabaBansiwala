package com.bababansiwalanew.Util.dto;

/**
 * Created by Lalit on 12-04-2017.
 */

public class BankListObject {

    private String BankId;
    private String BankName;
    private String AccNolimit;
    private String ShortCode;
    private String IMPS;
    private String NEFT;
    private String AccVeri;
    private String IFSC;

    public String getBankId() {
        return BankId;
    }

    public void setBankId(String bankId) {
        BankId = bankId;
    }

    public String getBankName() {
        return BankName;
    }

    public void setBankName(String bankName) {
        BankName = bankName;
    }

    public String getAccNolimit() {
        return AccNolimit;
    }

    public void setAccNolimit(String accNolimit) {
        AccNolimit = accNolimit;
    }

    public String getShortCode() {
        return ShortCode;
    }

    public void setShortCode(String shortCode) {
        ShortCode = shortCode;
    }

    public String getIMPS() {
        return IMPS;
    }

    public void setIMPS(String IMPS) {
        this.IMPS = IMPS;
    }

    public String getNEFT() {
        return NEFT;
    }

    public void setNEFT(String NEFT) {
        this.NEFT = NEFT;
    }

    public String getAccVeri() {
        return AccVeri;
    }

    public void setAccVeri(String accVeri) {
        AccVeri = accVeri;
    }

    public String getIFSC() {
        return IFSC;
    }

    public void setIFSC(String IFSC) {
        this.IFSC = IFSC;
    }
}
