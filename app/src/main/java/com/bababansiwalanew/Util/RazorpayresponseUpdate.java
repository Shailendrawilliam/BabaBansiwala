package com.bababansiwalanew.Util;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RazorpayresponseUpdate {


    @SerializedName("status")
    @Expose
    public String status;
    @SerializedName("order_id")
    @Expose
    public String order_id;
    @SerializedName("id")
    @Expose
    public String id;
    @SerializedName("entity")
    @Expose
    public String entity;
    @SerializedName("created_at")
    @Expose
    public String created_at;
    @SerializedName("amount")
    @Expose
    public String amount;
    @SerializedName("method")
    @Expose
    public String method;
    @SerializedName("amount_refunded")
    @Expose
    public String amount_refunded;
    @SerializedName("refund_status")
    @Expose
    public String refund_status;

    @SerializedName("card_id")
    @Expose
    private String card_id;

    @SerializedName("bank")
    @Expose
    private String bank;
    @SerializedName("wallet")
    @Expose
    private String wallet;
    @SerializedName("contact")
    @Expose
    private String contact;




    public RazorpayresponseUpdate(  String status, String order_id, String id, String entity, String created_at,
                                    String amount, String method, String amount_refunded, String refund_status,
                                    String card_id,String bank,String wallet,String contact) {

        this.status = status;
        this.order_id = order_id;
        this.id = id;
        this.entity = entity;
        this.created_at = created_at;
        this.amount = amount;
        this.method = method;
        this.amount_refunded = amount_refunded;
        this.refund_status = refund_status;
        this.card_id = card_id;
        this.bank = bank;
        this.wallet = wallet;
        this.contact = contact;



    }
}
