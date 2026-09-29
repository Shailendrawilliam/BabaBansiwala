package com.bababansiwalanew.Util.dto;

/**
 * Created by Lalit on 18-04-2017.
 */

public class BankDetail {

    private String ID;
    private String BankName;
    private String AccountNo;
    private String Islogin;

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getBankName() {
        return BankName;
    }

    public void setBankName(String bankName) {
        BankName = bankName;
    }

    public String getAccountNo() {
        return AccountNo;
    }

    public void setAccountNo(String accountNo) {
        AccountNo = accountNo;
    }

    public String getIslogin() {
        return Islogin;
    }

    public void setIslogin(String islogin) {
        Islogin = islogin;
    }
}
