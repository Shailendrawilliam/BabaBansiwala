package com.bababansiwalanew.AddMoney;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Merchant_order_id {
    @SerializedName("merchant_order_id")
    @Expose
    private String merchant_order_id;

    public String getMerchant_order_id() {
        return merchant_order_id;
    }

    public void setMerchant_order_id(String merchant_order_id) {
        this.merchant_order_id = merchant_order_id;
    }
}
