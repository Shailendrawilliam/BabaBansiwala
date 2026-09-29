package com.bababansiwalanew.Util;

import com.bababansiwalanew.ecomerrce.modal.ListProductCategory;
import com.shopping.OrderItem;

import java.util.ArrayList;
import java.util.List;

public class CatgoryResponse {

    private String RESPONSESTATUS;


    private String message;

    
    private ArrayList<ListProductCategory> listProductCategory = null;

    
    private ArrayList<ListProductCategory> listProductSubCategory;

    
    private ArrayList<ListProductCategory> listEcommerceProduct;
    private ArrayList<OrderItem> listBuyProduct;

    public ArrayList<OrderItem> getListBuyProduct() {
        return listBuyProduct;
    }

    public void setListBuyProduct(ArrayList<OrderItem> listBuyProduct) {
        this.listBuyProduct = listBuyProduct;
    }

    public String getResponsestatus() {
        return RESPONSESTATUS;
    }

    public void setResponsestatus(String responsestatus) {
        this.RESPONSESTATUS = responsestatus;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<ListProductCategory> getListProductCategory() {
        return listProductCategory;
    }

    public void setListProductCategory(ArrayList<ListProductCategory> listProductCategory) {
        this.listProductCategory = listProductCategory;
    }

    public String getRESPONSESTATUS() {
        return RESPONSESTATUS;
    }

    public void setRESPONSESTATUS(String RESPONSESTATUS) {
        this.RESPONSESTATUS = RESPONSESTATUS;
    }

    public ArrayList<ListProductCategory> getListProductSubCategory() {
        return listProductSubCategory;
    }

    public void setListProductSubCategory(ArrayList<ListProductCategory> listProductSubCategory) {
        this.listProductSubCategory = listProductSubCategory;
    }

    public ArrayList<ListProductCategory> getListEcommerceProduct() {
        return listEcommerceProduct;
    }

    public void setListEcommerceProduct(ArrayList<ListProductCategory> listEcommerceProduct) {
        this.listEcommerceProduct = listEcommerceProduct;
    }
}
