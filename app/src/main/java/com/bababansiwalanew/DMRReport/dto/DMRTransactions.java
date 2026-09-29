package com.bababansiwalanew.DMRReport.dto;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Lalit on 18-04-2017.
 */

public class DMRTransactions {

    private String ID;
    private String Transaction_ID;
    private String VenderID;
    private String SenderMobileNo;
    private String Utility_Balance;
    private String Outletname;
    private String Amounttransfer_to;
    @SerializedName(value = "amount",alternate = "Amount")
    @Expose
    private String amount;
    private String Balanceamt;
    private String Type;
    private String Operatorid;
    private String OpraterName;
    private String Oprater_Id;
    private String IsDisputable;
    private String Creatd_Date;
    private String RequestedAmount;
    private String RefundRequest;
    private String Response_Time;
    private String TotalChargeAmount;
    private String TransferType;

    public String getTotalChargeAmount() {
        return TotalChargeAmount;
    }

    public void setTotalChargeAmount(String totalChargeAmount) {
        TotalChargeAmount = totalChargeAmount;
    }

    public String getTransferType() {
        return TransferType;
    }

    public void setTransferType(String transferType) {
        TransferType = transferType;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getTransaction_ID() {
        return Transaction_ID;
    }

    public void setTransaction_ID(String transaction_ID) {
        Transaction_ID = transaction_ID;
    }

    public String getVenderID() {
        return VenderID;
    }

    public void setVenderID(String venderID) {
        VenderID = venderID;
    }

    public String getSenderMobileNo() {
        return SenderMobileNo;
    }

    public void setSenderMobileNo(String senderMobileNo) {
        SenderMobileNo = senderMobileNo;
    }

    public String getUtility_Balance() {
        return Utility_Balance;
    }

    public void setUtility_Balance(String utility_Balance) {
        Utility_Balance = utility_Balance;
    }

    public String getOutletname() {
        return Outletname;
    }

    public void setOutletname(String outletname) {
        Outletname = outletname;
    }

    public String getAmounttransfer_to() {
        return Amounttransfer_to;
    }

    public void setAmounttransfer_to(String amounttransfer_to) {
        Amounttransfer_to = amounttransfer_to;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getBalanceamt() {
        return Balanceamt;
    }

    public void setBalanceamt(String balanceamt) {
        Balanceamt = balanceamt;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public String getOperatorid() {
        return Operatorid;
    }

    public void setOperatorid(String operatorid) {
        Operatorid = operatorid;
    }

    public String getOpraterName() {
        return OpraterName;
    }

    public void setOpraterName(String opraterName) {
        OpraterName = opraterName;
    }

    public String getOprater_Id() {
        return Oprater_Id;
    }

    public void setOprater_Id(String oprater_Id) {
        Oprater_Id = oprater_Id;
    }

    public String getIsDisputable() {
        return IsDisputable;
    }

    public void setIsDisputable(String isDisputable) {
        IsDisputable = isDisputable;
    }

    public String getCreatd_Date() {
        return Creatd_Date;
    }

    public void setCreatd_Date(String creatd_Date) {
        Creatd_Date = creatd_Date;
    }

    public String getRequestedAmount() {
        return RequestedAmount;
    }

    public void setRequestedAmount(String requestedAmount) {
        RequestedAmount = requestedAmount;
    }

    public String getRefundRequest() {
        return RefundRequest;
    }

    public void setRefundRequest(String refundRequest) {
        RefundRequest = refundRequest;
    }

    public String getResponse_Time() {
        return Response_Time;
    }

    public void setResponse_Time(String response_Time) {
        Response_Time = response_Time;
    }
}
