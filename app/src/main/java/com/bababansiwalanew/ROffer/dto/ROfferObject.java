package com.bababansiwalanew.ROffer.dto;

/**
 * Created by Lalit on 16-04-2017.
 */

public class ROfferObject {

    private String rs;
    private String desc;


    private String MonthlyRecharge;
    private String Balance;
    private String customerName;
    private String status;
    private String NextRechargeDate;
    private String planname;
    private String lastrechargeamount;
    private String lastrechargedate;



    public String getRs() {
        return rs;
    }

    public void setRs(String rs) {
        this.rs = rs;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getMonthlyRecharge() {
        return MonthlyRecharge;
    }

    public void setMonthlyRecharge(String monthlyRecharge) {
        MonthlyRecharge = monthlyRecharge;
    }

    public String getBalance() {
        return Balance;
    }

    public void setBalance(String balance) {
        Balance = balance;
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
        return NextRechargeDate;
    }

    public void setNextRechargeDate(String nextRechargeDate) {
        NextRechargeDate = nextRechargeDate;
    }

    public String getPlanname() {
        return planname;
    }

    public void setPlanname(String planname) {
        this.planname = planname;
    }

    public String getLastrechargeamount() {
        return lastrechargeamount;
    }

    public void setLastrechargeamount(String lastrechargeamount) {
        this.lastrechargeamount = lastrechargeamount;
    }

    public String getLastrechargedate() {
        return lastrechargedate;
    }

    public void setLastrechargedate(String lastrechargedate) {
        this.lastrechargedate = lastrechargedate;
    }
}
