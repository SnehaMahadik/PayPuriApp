package com.benow.models;

import java.util.ArrayList;
import java.util.List;

public class EcommOrder {

    private String id;
    private String orderId;
    private Double orderAmount;
    private String orderDate;
    private String deliveryDate;
    private String orderDescription;
    private String username;
    private String imageUrl;
    private String orderStatus;
    private String merchantCode;
    private ShippingAddress shippingAddress;
    private BillingAddress billingAddress;
    private List<OrderItemDetail> orderItemDetails = new ArrayList<OrderItemDetail>();

    /**
     * @return The id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id The id
     */
    public void setId(String id) {
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

    /**
     * @return The username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username The username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return The imageUrl
     */
    public String getImageUrl() {
        return imageUrl;
    }

    /**
     * @param imageUrl The imageUrl
     */
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    /**
     * @return The orderStatus
     */
    public String getOrderStatus() {
        return orderStatus;
    }

    /**
     * @param orderStatus The orderStatus
     */
    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    /**
     * @return The merchantCode
     */
    public String getMerchantCode() {
        return merchantCode;
    }

    /**
     * @param merchantCode The merchantCode
     */
    public void setMerchantCode(String merchantCode) {
        this.merchantCode = merchantCode;
    }

    /**
     * @return The shippingAddress
     */
    public ShippingAddress getShippingAddress() {
        return shippingAddress;
    }

    /**
     * @param shippingAddress The shippingAddress
     */
    public void setShippingAddress(ShippingAddress shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    /**
     * @return The billingAddress
     */
    public BillingAddress getBillingAddress() {
        return billingAddress;
    }

    /**
     * @param billingAddress The billingAddress
     */
    public void setBillingAddress(BillingAddress billingAddress) {
        this.billingAddress = billingAddress;
    }

    /**
     * @return The orderItemDetails
     */
    public List<OrderItemDetail> getOrderItemDetails() {
        return orderItemDetails;
    }

    /**
     * @param orderItemDetails The orderItemDetails
     */
    public void setOrderItemDetails(List<OrderItemDetail> orderItemDetails) {
        this.orderItemDetails = orderItemDetails;
    }

}