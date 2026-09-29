package com.bababansiwalanew.DisputeReport.dto;

/**
 * Created by Lalit on 18-04-2017.
 */

public class Dispute {

    private String Column1;
    private String RDate;
    private String TID;
    private String RechargeNo;
    private String Amount;
    private String Status;
    private String RefundRequest;
    private String Remark;

    public String getColumn1() {
        return Column1;
    }

    public void setColumn1(String column1) {
        Column1 = column1;
    }

    public String getRDate() {
        return RDate;
    }

    public void setRDate(String RDate) {
        this.RDate = RDate;
    }

    public String getTID() {
        return TID;
    }

    public void setTID(String TID) {
        this.TID = TID;
    }

    public String getRechargeNo() {
        return RechargeNo;
    }

    public void setRechargeNo(String rechargeNo) {
        RechargeNo = rechargeNo;
    }

    public String getAmount() {
        return Amount;
    }

    public void setAmount(String amount) {
        Amount = amount;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public String getRefundRequest() {
        return RefundRequest;
    }

    public void setRefundRequest(String refundRequest) {
        RefundRequest = refundRequest;
    }

    public String getRemark() {
        return Remark;
    }

    public void setRemark(String remark) {
        Remark = remark;
    }
}
