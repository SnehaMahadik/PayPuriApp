package com.benow.models;


public class PeerPayResponse {

    private PaymentTransactionStatus paymentTransactionStatus;
    private String userBankAccountMaskedNumber;
    private String peerBankAccountMaskedNumber;
    private String peerMobileNumber;
    private Double amountPaid;
    private String transactionNumber;
    private String transactionDate;

    /**
     * @return The paymentTransactionStatus
     */
    public PaymentTransactionStatus getPaymentTransactionStatus() {
        return paymentTransactionStatus;
    }

    /**
     * @param paymentTransactionStatus The paymentTransactionStatus
     */
    public void setPaymentTransactionStatus(PaymentTransactionStatus paymentTransactionStatus) {
        this.paymentTransactionStatus = paymentTransactionStatus;
    }

    /**
     * @return The userBankAccountMaskedNumber
     */
    public Object getUserBankAccountMaskedNumber() {
        return userBankAccountMaskedNumber;
    }

    /**
     * @param userBankAccountMaskedNumber The userBankAccountMaskedNumber
     */
    public void setUserBankAccountMaskedNumber(String userBankAccountMaskedNumber) {
        this.userBankAccountMaskedNumber = userBankAccountMaskedNumber;
    }

    /**
     * @return The peerBankAccountMaskedNumber
     */
    public String getPeerBankAccountMaskedNumber() {
        return peerBankAccountMaskedNumber;
    }

    /**
     * @param peerBankAccountMaskedNumber The peerBankAccountMaskedNumber
     */
    public void setPeerBankAccountMaskedNumber(String peerBankAccountMaskedNumber) {
        this.peerBankAccountMaskedNumber = peerBankAccountMaskedNumber;
    }

    /**
     * @return The peerMobileNumber
     */
    public String getPeerMobileNumber() {
        return peerMobileNumber;
    }

    /**
     * @param peerMobileNumber The peerMobileNumber
     */
    public void setPeerMobileNumber(String peerMobileNumber) {
        this.peerMobileNumber = peerMobileNumber;
    }

    /**
     * @return The amountPaid
     */
    public Double getAmountPaid() {
        return amountPaid;
    }

    /**
     * @param amountPaid The amountPaid
     */
    public void setAmountPaid(Double amountPaid) {
        this.amountPaid = amountPaid;
    }

    /**
     * @return The transactionNumber
     */
    public String getTransactionNumber() {
        return transactionNumber;
    }

    /**
     * @param transactionNumber The transactionNumber
     */
    public void setTransactionNumber(String transactionNumber) {
        this.transactionNumber = transactionNumber;
    }

    /**
     * @return The transactionDate
     */
    public String getTransactionDate() {
        return transactionDate;
    }

    /**
     * @param transactionDate The transactionDate
     */
    public void setTransactionDate(String transactionDate) {
        this.transactionDate = transactionDate;
    }

}

