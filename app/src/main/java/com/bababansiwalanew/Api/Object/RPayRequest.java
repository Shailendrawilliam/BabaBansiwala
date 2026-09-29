package com.bababansiwalanew.Api.Object;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RPayRequest {
   /* @SerializedName("key_id")
    @Expose
    private String keyId;
    @SerializedName("order_id")
    @Expose
    private String orderId;
    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("image")
    @Expose
    private Object image;
    @SerializedName("Prefill_name")
    @Expose
    private String prefillName;
    @SerializedName("Prefill_contact")
    @Expose
    private String prefillContact;
    @SerializedName("Prefill_email")
    @Expose
    private String prefillEmail;*/

    @SerializedName("amount")
    @Expose
    private Integer amount;
    @SerializedName("description")
    @Expose
    private Object description;

    @SerializedName("key_id")
    @Expose
    private String key_id;
    @SerializedName("order_id")
    @Expose
    private String order_id;
    @SerializedName("key_Secret")
    @Expose
    private String key_Secret;

    public String getKey_Secret() {
        return key_Secret;
    }

    public void setKey_Secret(String key_Secret) {
        this.key_Secret = key_Secret;
    }
    public Object getDescription() {
        return description;
    }

    public void setDescription(Object description) {
        this.description = description;
    }

    @SerializedName("Prefill_name")
    @Expose
    private String Prefill_name;
    @SerializedName("Prefill_contact")
    @Expose
    private String Prefill_contact;
    @SerializedName("Prefill_email")
    @Expose
    private String Prefill_email;
    @SerializedName("callback_url")
    @Expose
    private String callback_url;
    @SerializedName("cancel_url")
    @Expose
    private String cancel_url;

   /* public String getKeyId() {
        return keyId;
    }

    public void setKeyId(String keyId) {
        this.keyId = keyId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }



    public Object getImage() {
        return image;
    }

    public void setImage(Object image) {
        this.image = image;
    }

    public String getPrefillName() {
        return prefillName;
    }

    public void setPrefillName(String prefillName) {
        this.prefillName = prefillName;
    }

    public String getPrefillContact() {
        return prefillContact;
    }

    public void setPrefillContact(String prefillContact) {
        this.prefillContact = prefillContact;
    }

    public String getPrefillEmail() {
        return prefillEmail;
    }

    public void setPrefillEmail(String prefillEmail) {
        this.prefillEmail = prefillEmail;
    }*/


    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }


    public String getKey_id() {
        return key_id;
    }

    public void setKey_id(String key_id) {
        this.key_id = key_id;
    }

    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }





  //  public void setImage(String image) {
       // this.image = image;
   // }

    public String getPrefill_name() {
        return Prefill_name;
    }

    public void setPrefill_name(String prefill_name) {
        Prefill_name = prefill_name;
    }

    public String getPrefill_contact() {
        return Prefill_contact;
    }

    public void setPrefill_contact(String prefill_contact) {
        Prefill_contact = prefill_contact;
    }

    public String getPrefill_email() {
        return Prefill_email;
    }

    public void setPrefill_email(String prefill_email) {
        Prefill_email = prefill_email;
    }

    public String getCallback_url() {
        return callback_url;
    }

    public void setCallback_url(String callback_url) {
        this.callback_url = callback_url;
    }

    public String getCancel_url() {
        return cancel_url;
    }

    public void setCancel_url(String cancel_url) {
        this.cancel_url = cancel_url;
    }



}