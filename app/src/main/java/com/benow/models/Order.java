package com.benow.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Order implements Parcelable {

    private Integer id;
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
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

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

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }


    protected Order(Parcel in) {
        id = in.readByte() == 0x00 ? null : in.readInt();
        orderId = in.readString();
        orderAmount = in.readByte() == 0x00 ? null : in.readDouble();
        orderDate = in.readString();
        deliveryDate = in.readString();
        orderDescription = in.readString();
        username = in.readString();
        imageUrl = in.readString();
        orderStatus = in.readString();
        merchantCode = in.readString();
        shippingAddress = (ShippingAddress) in.readValue(ShippingAddress.class.getClassLoader());
        billingAddress = (BillingAddress) in.readValue(BillingAddress.class.getClassLoader());
        if (in.readByte() == 0x01) {
            orderItemDetails = new ArrayList<OrderItemDetail>();
            in.readList(orderItemDetails, OrderItemDetail.class.getClassLoader());
        } else {
            orderItemDetails = null;
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (id == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeInt(id);
        }
        dest.writeString(orderId);
        if (orderAmount == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeDouble(orderAmount);
        }
        dest.writeString(orderDate);
        dest.writeString(deliveryDate);
        dest.writeString(orderDescription);
        dest.writeString(username);
        dest.writeString(imageUrl);
        dest.writeString(orderStatus);
        dest.writeString(merchantCode);
        dest.writeValue(shippingAddress);
        dest.writeValue(billingAddress);
        if (orderItemDetails == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeList(orderItemDetails);
        }
    }

    @SuppressWarnings("unused")
    public static final Creator<Order> CREATOR = new Creator<Order>() {
        @Override
        public Order createFromParcel(Parcel in) {
            return new Order(in);
        }

        @Override
        public Order[] newArray(int size) {
            return new Order[size];
        }
    };
}