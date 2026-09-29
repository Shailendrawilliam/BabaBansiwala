package com.bababansiwalanew.Dashboard.ui;

import java.util.ArrayList;

public class DownlineUserResponse {

    private String RESPONSESTATUS;
    private String message,Message;
    private ArrayList<UserRole> RoleLIst;
    private ArrayList<UserRole> User;
    private ArrayList<UserRole> StockList;
    private ArrayList<UserRole> RechargeList;
    private ArrayList<UserRole> BusinessList;

    public ArrayList<UserRole> getBusinessList() {
        return BusinessList;
    }

    public void setBusinessList(ArrayList<UserRole> businessList) {
        BusinessList = businessList;
    }

    public ArrayList<UserRole> getStockList() {
        return StockList;
    }

    public void setStockList(ArrayList<UserRole> stockList) {
        StockList = stockList;
    }

    public ArrayList<UserRole> getRechargeList() {
        return RechargeList;
    }

    public void setRechargeList(ArrayList<UserRole> rechargeList) {
        RechargeList = rechargeList;
    }

    public ArrayList<UserRole> getRoleLIst() {
        return RoleLIst;
    }

    public void setRoleLIst(ArrayList<UserRole> roleLIst) {
        RoleLIst = roleLIst;
    }

    public String getRESPONSESTATUS() {
        return RESPONSESTATUS;
    }

    public void setRESPONSESTATUS(String RESPONSESTATUS) {
        this.RESPONSESTATUS = RESPONSESTATUS;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ArrayList<UserRole> getUser() {
        return User;
    }

    public void setUser(ArrayList<UserRole> user) {
        User = user;
    }
}
