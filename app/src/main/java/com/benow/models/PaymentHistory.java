package com.benow.models;

/**
 * Created by sneha13498 on 2/20/2016.
 */

public class PaymentHistory {

    private String orderId;

    private String username;

    private String transactionStatus;

    private String merchantCode;

    private String packetId;

    private Double pacakgeItemsAmount;
    private String bankAccountRefNumber;

    private String bankAccountMaskedNumber;

    private String bankAccountName;

    private String referralCode;

    private String deliveryDate;

    private String transactionDate;
    private String transactionErrorCode;

    public String getTransactionErrorCode() {
        return transactionErrorCode;
    }

    public void setTransactionErrorCode(String transactionErrorCode) {
        this.transactionErrorCode = transactionErrorCode;
    }

    /**
     *
     * @return
     * The orderId
     */
    public String getOrderId() {
        return orderId;
    }

    /**
     *
     * @param orderId
     * The orderId
     */
    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    /**
     *
     * @return
     * The username
     */
    public String getUsername() {
        return username;
    }

    /**
     *
     * @param username
     * The username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     *
     * @return
     * The transactionStatus
     */
    public String getTransactionStatus() {
        return transactionStatus;
    }

    /**
     *
     * @param transactionStatus
     * The transactionStatus
     */
    public void setTransactionStatus(String transactionStatus) {
        this.transactionStatus = transactionStatus;
    }

    /**
     *
     * @return
     * The merchantCode
     */
    public String getMerchantCode() {
        return merchantCode;
    }

    /**
     *
     * @param merchantCode
     * The merchantCode
     */
    public void setMerchantCode(String merchantCode) {
        this.merchantCode = merchantCode;
    }

    /**
     *
     * @return
     * The packetId
     */
    public String getPacketId() {
        return packetId;
    }

    /**
     *
     * @param packetId
     * The packetId
     */
    public void setPacketId(String packetId) {
        this.packetId = packetId;
    }

    /**
     *
     * @return
     * The pacakgeItemsAmount
     */
    public Double getPacakgeItemsAmount() {
        return pacakgeItemsAmount;
    }

    /**
     *
     * @param pacakgeItemsAmount
     * The pacakgeItemsAmount
     */
    public void setPacakgeItemsAmount(Double pacakgeItemsAmount) {
        this.pacakgeItemsAmount = pacakgeItemsAmount;
    }

    /**
     *
     * @return
     * The transactionErrorCode
     */


    /**
     *
     * @param transactionErrorCode
     * The transactionErrorCode
     */


    /**
     *
     * @return
     * The bankAccountRefNumber
     */
    public String getBankAccountRefNumber() {
        return bankAccountRefNumber;
    }

    /**
     *
     * @param bankAccountRefNumber
     * The bankAccountRefNumber
     */
    public void setBankAccountRefNumber(String bankAccountRefNumber) {
        this.bankAccountRefNumber = bankAccountRefNumber;
    }

    /**
     *
     * @return
     * The bankAccountMaskedNumber
     */
    public String getBankAccountMaskedNumber() {
        return bankAccountMaskedNumber;
    }

    /**
     *
     * @param bankAccountMaskedNumber
     * The bankAccountMaskedNumber
     */
    public void setBankAccountMaskedNumber(String bankAccountMaskedNumber) {
        this.bankAccountMaskedNumber = bankAccountMaskedNumber;
    }

    /**
     *
     * @return
     * The bankAccountName
     */
    public String getBankAccountName() {
        return bankAccountName;
    }

    /**
     *
     * @param bankAccountName
     * The bankAccountName
     */
    public void setBankAccountName(String bankAccountName) {
        this.bankAccountName = bankAccountName;
    }

    /**
     *
     * @return
     * The referralCode
     */
    public String getReferralCode() {
        return referralCode;
    }

    /**
     *
     * @param referralCode
     * The referralCode
     */
    public void setReferralCode(String referralCode) {
        this.referralCode = referralCode;
    }

    /**
     *
     * @return
     * The deliveryDate
     */
    public String getDeliveryDate() {
        return deliveryDate;
    }

    /**
     *
     * @param deliveryDate
     * The deliveryDate
     */
    public void setDeliveryDate(String deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    /**
     *
     * @return
     * The transactionDate
     */
    public String getTransactionDate() {
        return transactionDate;
    }

    /**
     *
     * @param transactionDate
     * The transactionDate
     */
    public void setTransactionDate(String transactionDate) {
        this.transactionDate = transactionDate;
    }



}