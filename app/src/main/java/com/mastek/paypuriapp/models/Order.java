package com.mastek.paypuriapp.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Order {

    private Integer id;
    private String orderId;
    private Double orderAmount;
    private String orderDate;
    private String deliveryDate;
    private String orderDescription;
    private ArrayList<OrderItemDetail> orderItemDetails = new ArrayList<OrderItemDetail>();

    public ArrayList<OrderItemDetail> getOrderItemDetails() {
        return orderItemDetails;
    }

    public void setOrderItemDetails(ArrayList<OrderItemDetail> orderItemDetails) {
        this.orderItemDetails = orderItemDetails;
    }

    /**
     * @return The id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id The id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return The orderId
     */
    public String getOrderId() {
        return orderId;
    }

    /**
     * @param orderId The orderId
     */
    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    /**
     * @return The orderAmount
     */
    public Double getOrderAmount() {
        return orderAmount;
    }

    /**
     * @param orderAmount The orderAmount
     */
    public void setOrderAmount(Double orderAmount) {
        this.orderAmount = orderAmount;
    }

    /**
     * @return The orderDate
     */
    public String getOrderDate() {
        return orderDate;
    }

    /**
     * @param orderDate The orderDate
     */
    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    /**
     * @return The deliveryDate
     */
    public String getDeliveryDate() {
        return deliveryDate;
    }

    /**
     * @param deliveryDate The deliveryDate
     */
    public void setDeliveryDate(String deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    /**
     * @return The orderDescription
     */
    public String getOrderDescription() {
        return orderDescription;
    }

    /**
     * @param orderDescription The orderDescription
     */
    public void setOrderDescription(String orderDescription) {
        this.orderDescription = orderDescription;
    }





}