package com.bababansiwalanew.Util.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


import java.util.ArrayList;



public class OperatorList {

    @SerializedName("RESPONSESTATUS")
    @Expose
    private String RESPONSESTATUS;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("prepaidOperator")
    @Expose
    private ArrayList<Operator> prepaidOperator = null;
    @SerializedName("postpaidOperator")
    @Expose
    private ArrayList<Operator> postpaidOperator = null;
    @SerializedName("dthOperator")
    @Expose
    private ArrayList<Operator> dthOperator = null;
    @SerializedName("landlineOperator")
    @Expose
    private ArrayList<Operator> landlineOperator = null;
    @SerializedName("electricityOperator")
    @Expose
    private ArrayList<Operator> electricityOperator = null;
    @SerializedName("GasOperator")
    @Expose
    private ArrayList<Operator> gasOperator = null;
    @SerializedName("InsuranceOperator")
    @Expose
    private ArrayList<Operator> insuranceOperator = null;
    @SerializedName("PaymentOperator")
    @Expose
    private ArrayList<Operator> PaymentOperator = null;

    private ArrayList<Operator> WaterOperator;

    private ArrayList<Operator> BroadBandOperator;

    public ArrayList<Operator> getBroadBandOperator() {
        return BroadBandOperator;
    }

    public void setBroadBandOperator(ArrayList<Operator> broadBandOperator) {
        BroadBandOperator = broadBandOperator;
    }

    public ArrayList<Operator> getWaterOperator() {
        return WaterOperator;
    }

    public void setWaterOperator(ArrayList<Operator> waterOperator) {
        WaterOperator = waterOperator;
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

    public ArrayList<Operator> getPrepaidOperator() {
        return prepaidOperator;
    }

    public void setPrepaidOperator(ArrayList<Operator> prepaidOperator) {
        this.prepaidOperator = prepaidOperator;
    }

    public ArrayList<Operator> getPostpaidOperator() {
        return postpaidOperator;
    }

    public void setPostpaidOperator(ArrayList<Operator> postpaidOperator) {
        this.postpaidOperator = postpaidOperator;
    }

    public ArrayList<Operator> getDthOperator() {
        return dthOperator;
    }

    public void setDthOperator(ArrayList<Operator> dthOperator) {
        this.dthOperator = dthOperator;
    }

    public ArrayList<Operator> getLandlineOperator() {
        return landlineOperator;
    }

    public void setLandlineOperator(ArrayList<Operator> landlineOperator) {
        this.landlineOperator = landlineOperator;
    }

    public ArrayList<Operator> getElectricityOperator() {
        return electricityOperator;
    }

    public void setElectricityOperator(ArrayList<Operator> electricityOperator) {
        this.electricityOperator = electricityOperator;
    }

    public ArrayList<Operator> getGasOperator() {
        return gasOperator;
    }

    public void setGasOperator(ArrayList<Operator> gasOperator) {
        this.gasOperator = gasOperator;
    }

    public ArrayList<Operator> getInsuranceOperator() {
        return insuranceOperator;
    }

    public void setInsuranceOperator(ArrayList<Operator> insuranceOperator) {
        this.insuranceOperator = insuranceOperator;
    }

    public ArrayList<Operator> getPaymentOperator() {
        return PaymentOperator;
    }

    public void setPaymentOperator(ArrayList<Operator> paymentOperator) {
        this.PaymentOperator = paymentOperator;
    }
}


    /*@SerializedName("RESPONSESTATUS")
    @Expose
    private String RESPONSESTATUS;
    @SerializedName("message")
    @Expose
    private String message;

    @SerializedName("prepaidOperator")
    @Expose
    private ArrayArrayList<Operator> prepaidOperator;
    @SerializedName("postpaidOperator")
    @Expose
    private ArrayArrayList<Operator> postpaidOperator;
    @SerializedName("dthOperator")
    @Expose
    private ArrayArrayList<Operator> dthOperator;
    @SerializedName("landlineOperator")
    @Expose
    private ArrayArrayList<Operator> landlineOperator;
    @SerializedName("electricityOperator")
    @Expose
    private ArrayArrayList<Operator> electricityOperator;
    //  private ArrayArrayList<Operator> gasOperator;

    private ArrayArrayList<Operator> BroadBandOperator;
    @SerializedName("GasOperator")
    @Expose
    private ArrayArrayList<Operator> GasOperator;
    private ArrayArrayList<SaleReportObject> SaleReport;
    @SerializedName("InsuranceOperator")
    @Expose
    private ArrayArrayList<Operator> InsuranceOperator;
    private ArrayArrayList<Operator> WaterOperator;

    private ArrayArrayList<Operator> PaymentOperator;

    public ArrayArrayList<Operator> getPaymentOperator() {
        return PaymentOperator;
    }

    public void setPaymentOperator(ArrayArrayList<Operator> paymentOperator) {
        PaymentOperator = paymentOperator;
    }

    private String status;

    private String circle;
    private int OPID;
    private String operator;
    private String opcode;

    public ArrayArrayList<Operator> getBroadBandOperator() {
        return BroadBandOperator;
    }

    public void setBroadBandOperator(ArrayArrayList<Operator> broadBandOperator) {
        BroadBandOperator = broadBandOperator;
    }


    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public int getOPID() {
        return OPID;
    }

    public void setOPID(int OPID) {
        this.OPID = OPID;
    }

    public String getOpcode() {
        return opcode;
    }

    public void setOpcode(String opcode) {
        this.opcode = opcode;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    public String getCircle() {
        return circle;
    }

    public void setCircle(String circle) {
        this.circle = circle;
    }

    public ArrayArrayList<SaleReportObject> getSaleReport() {
        return SaleReport;
    }

    public ArrayArrayList<Operator> getGasOperator() {
        return GasOperator;
    }

    public void setGasOperator(ArrayArrayList<Operator> gasOperator) {
        GasOperator = gasOperator;
    }

    public void setSaleReport(ArrayArrayList<SaleReportObject> saleReport) {
        SaleReport = saleReport;
    }

    public ArrayArrayList<Operator> getInsuranceOperator() {
        return InsuranceOperator;
    }

    public void setInsuranceOperator(ArrayArrayList<Operator> insuranceOperator) {
        InsuranceOperator = insuranceOperator;
    }

    public ArrayArrayList<Operator> getWaterOperator() {
        return WaterOperator;
    }

    public void setWaterOperator(ArrayArrayList<Operator> waterOperator) {
        WaterOperator = waterOperator;
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

    public ArrayArrayList<Operator> getPrepaidOperator() {
        return prepaidOperator;
    }

    public void setPrepaidOperator(ArrayArrayList<Operator> prepaidOperator) {
        this.prepaidOperator = prepaidOperator;
    }

    public ArrayArrayList<Operator> getPostpaidOperator() {
        return postpaidOperator;
    }

    public void setPostpaidOperator(ArrayArrayList<Operator> postpaidOperator) {
        this.postpaidOperator = postpaidOperator;
    }

    public ArrayArrayList<Operator> getDthOperator() {
        return dthOperator;
    }

    public void setDthOperator(ArrayArrayList<Operator> dthOperator) {
        this.dthOperator = dthOperator;
    }

    public ArrayArrayList<Operator> getLandlineOperator() {
        return landlineOperator;
    }

    public void setLandlineOperator(ArrayArrayList<Operator> landlineOperator) {
        this.landlineOperator = landlineOperator;
    }

    public ArrayArrayList<Operator> getElectricityOperator() {
        return electricityOperator;
    }

    public void setElectricityOperator(ArrayArrayList<Operator> electricityOperator) {
        this.electricityOperator = electricityOperator;
    }


}
*/