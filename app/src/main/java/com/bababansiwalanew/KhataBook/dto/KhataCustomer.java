package com.bababansiwalanew.KhataBook.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class KhataCustomer implements Serializable {

    @SerializedName(value = "CustomerId", alternate = {"CustomerID", "Id", "ID", "id", "customerId"})
    @Expose
    private String customerId;

    @SerializedName(value = "Name", alternate = {"CustomerName", "name", "customerName"})
    @Expose
    private String name;

    @SerializedName(value = "Mobile", alternate = {"MobileNo", "mobile", "phone", "Phone"})
    @Expose
    private String mobile;

    @SerializedName(value = "Address", alternate = {"address"})
    @Expose
    private String address;

    @SerializedName(value = "Amount", alternate = {"Balance", "BalanceAmount", "TotalBalance", "amount", "balance"})
    @Expose
    private String balance;

    @SerializedName(value = "CreatedDate", alternate = {"Date", "EntryDate", "createdDate"})
    @Expose
    private String createdDate;

    @SerializedName(value = "Status", alternate = {"status"})
    @Expose
    private String status;

    public KhataCustomer() {
    }

    public KhataCustomer(String customerId, String name, String mobile, String address, String balance) {
        this.customerId = customerId;
        this.name = name;
        this.mobile = mobile;
        this.address = address;
        this.balance = balance;
    }

    public String getCustomerId() {
        return customerId != null ? customerId : "";
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getName() {
        return name != null ? name : "";
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile != null ? mobile : "";
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getAddress() {
        return address != null ? address : "";
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getBalance() {
        return balance != null ? balance : "0";
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public String getCreatedDate() {
        return createdDate != null ? createdDate : "";
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String getStatus() {
        return status != null ? status : "";
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
