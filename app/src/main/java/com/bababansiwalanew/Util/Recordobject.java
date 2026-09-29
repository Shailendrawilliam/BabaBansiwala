package com.bababansiwalanew.Util;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Recordobject {

    @SerializedName("MonthlyRecharge")
    @Expose
    private Integer monthlyRecharge;
    @SerializedName("Balance")
    @Expose
    private Double balance;
    @SerializedName("customerName")
    @Expose
    private String customerName;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("NextRechargeDate")
    @Expose
    private String nextRechargeDate;
    @SerializedName("planname")
    @Expose
    private String planname;

    public Integer getMonthlyRecharge() {
        return monthlyRecharge;
    }

    public void setMonthlyRecharge(Integer monthlyRecharge) {
        this.monthlyRecharge = monthlyRecharge;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getNextRechargeDate() {
        return nextRechargeDate;
    }

    public void setNextRechargeDate(String nextRechargeDate) {
        this.nextRechargeDate = nextRechargeDate;
    }

    public String getPlanname() {
        return planname;
    }

    public void setPlanname(String planname) {
        this.planname = planname;
    }

}