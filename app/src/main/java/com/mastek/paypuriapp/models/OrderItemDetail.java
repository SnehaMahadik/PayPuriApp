package com.mastek.paypuriapp.models;

import java.util.HashMap;
import java.util.Map;

public class OrderItemDetail {

    private Integer id;
    private String itemId;

    public Double getPacakgeItemsAmount() {
        return pacakgeItemsAmount;
    }

    public void setPacakgeItemsAmount(Double pacakgeItemsAmount) {
        this.pacakgeItemsAmount = pacakgeItemsAmount;
    }

    private Double pacakgeItemsAmount;
    private Double itemAmount;
    private String itemDeliveryDate;
    private String itemDescription;
    private String packetId;

    public String getPacketId() {
        return packetId;
    }

    public void setPacketId(String packetId) {
        this.packetId = packetId;
    }

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
     * @return The itemId
     */
    public String getItemId() {
        return itemId;
    }

    /**
     * @param itemId The itemId
     */
    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    /**
     * @return The itemAmount
     */
    public Double getItemAmount() {
        return itemAmount;
    }

    /**
     * @param itemAmount The itemAmount
     */
    public void setItemAmount(Double itemAmount) {
        this.itemAmount = itemAmount;
    }

    /**
     * @return The itemDeliveryDate
     */
    public String getItemDeliveryDate() {
        return itemDeliveryDate;
    }

    /**
     * @param itemDeliveryDate The itemDeliveryDate
     */
    public void setItemDeliveryDate(String itemDeliveryDate) {
        this.itemDeliveryDate = itemDeliveryDate;
    }

    /**
     * @return The itemDescription
     */
    public String getItemDescription() {
        return itemDescription;
    }

    /**
     * @param itemDescription The itemDescription
     */
    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}