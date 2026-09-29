package com.bababansiwalanew.UserDayBook.dto;

/**
 * Created by Lalit on 15-04-2017.
 */

public class UserDayBookSummary {

    private String OperatorId;
    private String OpraterName;
    private String TotalHits;
    private String TotalAmount;
    private String SuccessHits;
    private String SuccessAmount;
    private String FailedHits;
    private String FailedAmount;
    private String PendingHits;
    private String PendingAmount;
    private String Commission;

    public String getOperatorId() {
        return OperatorId;
    }

    public void setOperatorId(String operatorId) {
        OperatorId = operatorId;
    }

    public String getOpraterName() {
        return OpraterName;
    }

    public void setOpraterName(String opraterName) {
        OpraterName = opraterName;
    }

    public String getTotalHits() {
        return TotalHits;
    }

    public void setTotalHits(String totalHits) {
        TotalHits = totalHits;
    }

    public String getTotalAmount() {
        return TotalAmount;
    }

    public void setTotalAmount(String totalAmount) {
        TotalAmount = totalAmount;
    }

    public String getSuccessHits() {
        return SuccessHits;
    }

    public void setSuccessHits(String successHits) {
        SuccessHits = successHits;
    }

    public String getSuccessAmount() {
        return SuccessAmount;
    }

    public void setSuccessAmount(String successAmount) {
        SuccessAmount = successAmount;
    }

    public String getFailedHits() {
        return FailedHits;
    }

    public void setFailedHits(String failedHits) {
        FailedHits = failedHits;
    }

    public String getFailedAmount() {
        return FailedAmount;
    }

    public void setFailedAmount(String failedAmount) {
        FailedAmount = failedAmount;
    }

    public String getPendingHits() {
        return PendingHits;
    }

    public void setPendingHits(String pendingHits) {
        PendingHits = pendingHits;
    }

    public String getPendingAmount() {
        return PendingAmount;
    }

    public void setPendingAmount(String pendingAmount) {
        PendingAmount = pendingAmount;
    }

    public String getCommission() {
        return Commission;
    }

    public void setCommission(String commission) {
        Commission = commission;
    }
}
