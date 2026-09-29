package com.bababansiwalanew.DMR.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Recipient {
    @SerializedName("channel_absolute")
    @Expose
    private Integer channelAbsolute;
    @SerializedName("available_channel")
    @Expose
    private Integer availableChannel;
    @SerializedName("account_type")
    @Expose
    private Object accountType;
    @SerializedName("ifsc_status")
    @Expose
    private Integer ifscStatus;
    @SerializedName("is_self_account")
    @Expose
    private Object isSelfAccount;
    @SerializedName("channel")
    @Expose
    private Integer channel;
    @SerializedName("is_imps_scheduled")
    @Expose
    private Integer isImpsScheduled;
    @SerializedName("recipient_id_type")
    @Expose
    private Object recipientIdType;
    @SerializedName("imps_inactive_reason")
    @Expose
    private Object impsInactiveReason;
    @SerializedName("allowed_channel")
    @Expose
    private Integer allowedChannel;
    @SerializedName("is_verified")
    @Expose
    private Integer isVerified;
    @SerializedName("bank")
    @Expose
    private String bank;
    @SerializedName("is_otp_required")
    @Expose
    private Object isOtpRequired;
    @SerializedName("recipient_mobile")
    @Expose
    private String recipientMobile;
    @SerializedName("recipient_name")
    @Expose
    private String recipientName;
    @SerializedName("ifsc")
    @Expose
    private String ifsc;
    @SerializedName("account")
    @Expose
    private String account;
    @SerializedName("pipes")
    @Expose
    private Object pipes;
    @SerializedName("recipient_id")
    @Expose
    private String recipientId;
    @SerializedName("is_rblbc_recipient")
    @Expose
    private Integer isRblbcRecipient;

    public Integer getChannelAbsolute() {
        return channelAbsolute;
    }

    public void setChannelAbsolute(Integer channelAbsolute) {
        this.channelAbsolute = channelAbsolute;
    }

    public Integer getAvailableChannel() {
        return availableChannel;
    }

    public void setAvailableChannel(Integer availableChannel) {
        this.availableChannel = availableChannel;
    }

    public Object getAccountType() {
        return accountType;
    }

    public void setAccountType(Object accountType) {
        this.accountType = accountType;
    }

    public Integer getIfscStatus() {
        return ifscStatus;
    }

    public void setIfscStatus(Integer ifscStatus) {
        this.ifscStatus = ifscStatus;
    }

    public Object getIsSelfAccount() {
        return isSelfAccount;
    }

    public void setIsSelfAccount(Object isSelfAccount) {
        this.isSelfAccount = isSelfAccount;
    }

    public Integer getChannel() {
        return channel;
    }

    public void setChannel(Integer channel) {
        this.channel = channel;
    }

    public Integer getIsImpsScheduled() {
        return isImpsScheduled;
    }

    public void setIsImpsScheduled(Integer isImpsScheduled) {
        this.isImpsScheduled = isImpsScheduled;
    }

    public Object getRecipientIdType() {
        return recipientIdType;
    }

    public void setRecipientIdType(Object recipientIdType) {
        this.recipientIdType = recipientIdType;
    }

    public Object getImpsInactiveReason() {
        return impsInactiveReason;
    }

    public void setImpsInactiveReason(Object impsInactiveReason) {
        this.impsInactiveReason = impsInactiveReason;
    }

    public Integer getAllowedChannel() {
        return allowedChannel;
    }

    public void setAllowedChannel(Integer allowedChannel) {
        this.allowedChannel = allowedChannel;
    }

    public Integer getIsVerified() {
        return isVerified;
    }

    public void setIsVerified(Integer isVerified) {
        this.isVerified = isVerified;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public Object getIsOtpRequired() {
        return isOtpRequired;
    }

    public void setIsOtpRequired(Object isOtpRequired) {
        this.isOtpRequired = isOtpRequired;
    }

    public String getRecipientMobile() {
        return recipientMobile;
    }

    public void setRecipientMobile(String recipientMobile) {
        this.recipientMobile = recipientMobile;
    }

    public String getRecipientName() {
        return recipientName;
    }

    public void setRecipientName(String recipientName) {
        this.recipientName = recipientName;
    }

    public String getIfsc() {
        return ifsc;
    }

    public void setIfsc(String ifsc) {
        this.ifsc = ifsc;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public Object getPipes() {
        return pipes;
    }

    public void setPipes(Object pipes) {
        this.pipes = pipes;
    }

    public String getRecipientId() {
        return recipientId;
    }

    public void setRecipientId(String recipientId) {
        this.recipientId = recipientId;
    }

    public Integer getIsRblbcRecipient() {
        return isRblbcRecipient;
    }

    public void setIsRblbcRecipient(Integer isRblbcRecipient) {
        this.isRblbcRecipient = isRblbcRecipient;
    }

}
