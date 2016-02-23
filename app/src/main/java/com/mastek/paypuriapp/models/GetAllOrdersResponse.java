package com.mastek.paypuriapp.models;

import java.util.ArrayList;
import java.util.List;

public class GetAllOrdersResponse {

    private ArrayList<Order> orders = new ArrayList<Order>();

    /**
     * @return The orders
     */
    public ArrayList<Order> getOrders() {
        return orders;
    }

    /**
     * @param orders The orders
     */
    public void setOrders(ArrayList<Order> orders) {
        this.orders = orders;
    }


}