package com.example.demo.models.pendingorder;

import com.example.demo.entities.helperclasses.MyUUID;
import com.example.demo.models.pendingorder.nestedobjects.PendingOrderProductRequestModel;

import java.util.List;

public class PendingOrderRequestModel {

    private MyUUID storeUUID;
    private String customerUserName;
    private List<PendingOrderProductRequestModel> orderedProducts;

    public PendingOrderRequestModel(MyUUID storeUUID, String customerUserName,
                                    List<PendingOrderProductRequestModel> orderedProducts) {
        this.storeUUID = storeUUID;
        this.customerUserName = customerUserName;
        this.orderedProducts = orderedProducts;
    }

    public MyUUID getStoreUUID() {
        return storeUUID;
    }

    public void setStoreUUID(MyUUID storeUUID) {
        this.storeUUID = storeUUID;
    }

    public String getCustomerUserName() {
        return customerUserName;
    }

    public void setCustomerUserName(String customerUserName) {
        this.customerUserName = customerUserName;
    }

    public List<PendingOrderProductRequestModel> getOrderedProducts() {
        return orderedProducts;
    }

    public void setOrderedProducts(List<PendingOrderProductRequestModel> orderedProducts) {
        this.orderedProducts = orderedProducts;
    }
}
