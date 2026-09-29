package com.bababansiwalanew.UserDayBook.dto;

/**
 * Created by Lalit on 15-04-2017.
 */

public class UserDayBookLedger {

    private String CurrectAmount;
    private String OpeningBal;
    private String Receive;
    private String Transfer;
    private String RequestedAmount;
    private String Amount;
    private String Commission;
    private String HCommission;


    public String getCurrectAmount() {
        return CurrectAmount;
    }

    public void setCurrectAmount(String currectAmount) {
        CurrectAmount = currectAmount;
    }

    public String getOpeningBal() {
        return OpeningBal;
    }

    public void setOpeningBal(String openingBal) {
        OpeningBal = openingBal;
    }

    public String getReceive() {
        return Receive;
    }

    public void setReceive(String receive) {
        Receive = receive;
    }

    public String getTransfer() {
        return Transfer;
    }

    public void setTransfer(String transfer) {
        Transfer = transfer;
    }

    public String getRequestedAmount() {
        return RequestedAmount;
    }

    public void setRequestedAmount(String requestedAmount) {
        RequestedAmount = requestedAmount;
    }

    public String getAmount() {
        return Amount;
    }

    public void setAmount(String amount) {
        Amount = amount;
    }

    public String getCommission() {
        return Commission;
    }

    public void setCommission(String commission) {
        Commission = commission;
    }

    public String getHCommission() {
        return HCommission;
    }

    public void setHCommission(String HCommission) {
        this.HCommission = HCommission;
    }
}
