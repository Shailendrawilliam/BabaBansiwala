package com.bababansiwalanew.DMR.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DmrData {
    @SerializedName("customer_id_type")
    @Expose
    private Object customerIdType;
    @SerializedName("available_limit")
    @Expose
    private String availableLimit;
    @SerializedName("balance")
    @Expose
    private Object balance;
    @SerializedName("state_desc")
    @Expose
    private Object stateDesc;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("mobile")
    @Expose
    private String mobile;
    @SerializedName("limit")
    @Expose
    private Object limit;

    public String getRemiterid() {
        return remiterid;
    }

    public void setRemiterid(String remiterid) {
        this.remiterid = remiterid;
    }

    @SerializedName("remiterid")
    @Expose
    private String remiterid;
    @SerializedName("currency")
    @Expose
    private Object currency;
    @SerializedName("state")
    @Expose
    private Object state;
    @SerializedName("used_limit")
    @Expose
    private Integer usedLimit;
    @SerializedName("total_limit")
    @Expose
    private Integer totalLimit;
    @SerializedName("pan_required")
    @Expose
    private Integer panRequired;
    @SerializedName("recipient_list")
    @Expose
    private List<Object> recipientList = null;
    @SerializedName("remaining_limit_before_pan_required")
    @Expose
    private Integer remainingLimitBeforePanRequired;

    public Object getCustomerIdType() {
        return customerIdType;
    }

    public void setCustomerIdType(Object customerIdType) {
        this.customerIdType = customerIdType;
    }

    public String getAvailableLimit() {
        return availableLimit;
    }

    public void setAvailableLimit(String availableLimit) {
        this.availableLimit = availableLimit;
    }

    public Object getBalance() {
        return balance;
    }

    public void setBalance(Object balance) {
        this.balance = balance;
    }

    public Object getStateDesc() {
        return stateDesc;
    }

    public void setStateDesc(Object stateDesc) {
        this.stateDesc = stateDesc;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Object getLimit() {
        return limit;
    }

    public void setLimit(Object limit) {
        this.limit = limit;
    }

    public Object getCurrency() {
        return currency;
    }

    public void setCurrency(Object currency) {
        this.currency = currency;
    }

    public Object getState() {
        return state;
    }

    public void setState(Object state) {
        this.state = state;
    }

    public Integer getUsedLimit() {
        return usedLimit;
    }

    public void setUsedLimit(Integer usedLimit) {
        this.usedLimit = usedLimit;
    }

    public Integer getTotalLimit() {
        return totalLimit;
    }

    public void setTotalLimit(Integer totalLimit) {
        this.totalLimit = totalLimit;
    }

    public Integer getPanRequired() {
        return panRequired;
    }

    public void setPanRequired(Integer panRequired) {
        this.panRequired = panRequired;
    }

    public List<Object> getRecipientList() {
        return recipientList;
    }

    public void setRecipientList(List<Object> recipientList) {
        this.recipientList = recipientList;
    }

    public Integer getRemainingLimitBeforePanRequired() {
        return remainingLimitBeforePanRequired;
    }

    public void setRemainingLimitBeforePanRequired(Integer remainingLimitBeforePanRequired) {
        this.remainingLimitBeforePanRequired = remainingLimitBeforePanRequired;
    }
}
