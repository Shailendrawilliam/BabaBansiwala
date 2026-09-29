package com.bababansiwalanew.Dashboard.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.bababansiwalanew.Login.dto.LoginData;

import java.util.ArrayList;

public class RechargeInfoResponse {

    private String customername;
    private String customernumber;
    private String customeraddress;
    private String RESPONSESTATUS;
    private String ero;
    private String oldamount;
    private String newamount;
    private String billdate;
    private String billamount;
    private String mesage;
    @SerializedName(value = "message",alternate = "Message")
    @Expose
    private String message;
    private String dueamount;

//
//
//    @SerializedName("tel")
//    @Expose
    private String tel;
//    @SerializedName("operator")
//    @Expose
    private String operator;
//    @SerializedName("records")
//    @Expose
    private ArrayList<LoginData> records = null;
    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public ArrayList<LoginData> getRecords() {
        return records;
    }

    public void setRecords(ArrayList<LoginData> records) {
        this.records = records;
    }

    public String getReffid() {
        return Reffid;
    }

    public void setReffid(String reffid) {
        Reffid = reffid;
    }

    private String Reffid;
    private String duedate;

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

    public String getDueamount() {
        return dueamount;
    }

    public void setDueamount(String dueamount) {
        this.dueamount = dueamount;
    }

    public String getDuedate() {
        return duedate;
    }

    public void setDuedate(String duedate) {
        this.duedate = duedate;
    }

    public String getBillnumber() {
        return billnumber;
    }

    public void setBillnumber(String billnumber) {
        this.billnumber = billnumber;
    }

    private String billnumber;
    private String status;
    private String customerid;
    private String customerexpiry;
    private String customerbalance;
    private String customerpackage;
    private String customeraddons;
    private String customermobile;
    private String statuscode;
    private ArrayList<LoginData> data;


    public ArrayList<LoginData> getData() {
        return data;
    }

    public void setData(ArrayList<LoginData> data) {
        this.data = data;
    }

    public String getStatuscode() {
        return statuscode;
    }

    public void setStatuscode(String statuscode) {
        this.statuscode = statuscode;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCustomername() {
        return customername;
    }

    public void setCustomername(String customername) {
        this.customername = customername;
    }

    public String getCustomernumber() {
        return customernumber;
    }

    public void setCustomernumber(String customernumber) {
        this.customernumber = customernumber;
    }

    public String getCustomeraddress() {
        return customeraddress;
    }

    public void setCustomeraddress(String customeraddress) {
        this.customeraddress = customeraddress;
    }

    public String getEro() {
        return ero;
    }

    public void setEro(String ero) {
        this.ero = ero;
    }

    public String getOldamount() {
        return oldamount;
    }

    public void setOldamount(String oldamount) {
        this.oldamount = oldamount;
    }

    public String getNewamount() {
        return newamount;
    }

    public void setNewamount(String newamount) {
        this.newamount = newamount;
    }

    public String getBilldate() {
        return billdate;
    }

    public void setBilldate(String billdate) {
        this.billdate = billdate;
    }

    public String getBillamount() {
        return billamount;
    }

    public void setBillamount(String billamount) {
        this.billamount = billamount;
    }

    public String getMesage() {
        return mesage;
    }

    public void setMesage(String mesage) {
        this.mesage = mesage;
    }

    public String getCustomerid() {
        return customerid;
    }

    public void setCustomerid(String customerid) {
        this.customerid = customerid;
    }

    public String getCustomerexpiry() {
        return customerexpiry;
    }

    public void setCustomerexpiry(String customerexpiry) {
        this.customerexpiry = customerexpiry;
    }

    public String getCustomerbalance() {
        return customerbalance;
    }

    public void setCustomerbalance(String customerbalance) {
        this.customerbalance = customerbalance;
    }

    public String getCustomerpackage() {
        return customerpackage;
    }

    public void setCustomerpackage(String customerpackage) {
        this.customerpackage = customerpackage;
    }

    public String getCustomeraddons() {
        return customeraddons;
    }

    public void setCustomeraddons(String customeraddons) {
        this.customeraddons = customeraddons;
    }

    public String getCustomermobile() {
        return customermobile;
    }

    public void setCustomermobile(String customermobile) {
        this.customermobile = customermobile;
    }
}
